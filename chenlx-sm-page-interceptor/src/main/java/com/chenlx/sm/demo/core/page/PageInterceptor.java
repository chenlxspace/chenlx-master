package com.chenlx.sm.demo.core.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chenlx.sm.demo.core.util.ReflectUtil;

/**
 * MyBatis 3.4.0 分页插件需按如下方式写
 * 
 * 若@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
 * 将抛出如下异常：
 * Cause: org.apache.ibatis.plugin.PluginException: 
 * Could not find method on interface org.apache.ibatis.executor.statement.StatementHandler named prepare. 
 * Cause: java.lang.NoSuchMethodException: org.apache.ibatis.executor.statement.StatementHandler.prepare(java.sql.Connection)
 * 
 * https://github.com/mybatis/mybatis-3/issues/645
 * https://github.com/mybatis/mybatis-3/blob/master/src/main/java/org/apache/ibatis/executor/statement/StatementHandler.java#L33-L34
 * 
 * StatementHandler接口的prepare方法增加了一个Integer参数
 * 
 * @author Richard
 * @date 2017年3月13日
 * @time 上午11:41:51
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor {
    
    Logger logger = LoggerFactory.getLogger(PageInterceptor.class);
    
    private static final String SQL = "sql";

    private static final String DELEGATE = "delegate";
    
    private static final String MAPPED_STATEMENT = "mappedStatement";
    
    private static final String DATABASE_TYPE = "databaseType";
    
    private static final String MYSQL = "mysql";
    
    private static final String ORACLE = "oracle";
    
    
    private String databaseType;
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        
        final RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        
        final StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, DELEGATE);
        
        final BoundSql boundSql = delegate.getBoundSql();
        
        final Object obj = boundSql.getParameterObject();
        
        if (obj instanceof PageUtil) {
            final PageUtil pageUtil = (PageUtil) obj;
            MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, MAPPED_STATEMENT);
            Connection connection = (Connection) invocation.getArgs()[0];
            final String sql = boundSql.getSql();
            if (logger.isDebugEnabled()) {
                logger.debug("原始SQL：" + sql);
            }
            this.setTotalRecord(pageUtil, mappedStatement, connection);
            final String pageSql = this.getPageSql(pageUtil, sql);
            ReflectUtil.setFieldValue(boundSql, SQL, pageSql);
        }
        
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.databaseType = properties.getProperty(DATABASE_TYPE);
    }
    
    /**
     * 获取分页查询SQL
     * @param pageUtil
     * @param sql
     * @return
     */
    private String getPageSql(PageUtil pageUtil, String sql) {
        final StringBuffer sqlBuffer = new StringBuffer(sql);
        
        if (MYSQL.equalsIgnoreCase(databaseType)) {
            return getMySQLPageSql(pageUtil, sqlBuffer);
        } else if (ORACLE.equalsIgnoreCase(databaseType)) {
            return getOraclePageSql(pageUtil, sqlBuffer);
        }
        
        return sqlBuffer.toString();
    }
    
    /**
     * 获取MySQL 分页查询SQL
     * @param pageUtil
     * @param sqlBuffer
     * @return
     */
    private String getMySQLPageSql(PageUtil pageUtil, StringBuffer sqlBuffer) {
        sqlBuffer.append(" limit ");
        sqlBuffer.append(pageUtil.getPageIndex()*pageUtil.getPageSize());
        sqlBuffer.append(",");
        sqlBuffer.append(pageUtil.getPageSize());
        
        return sqlBuffer.toString();
    }
    
    /**
     * 获取Oracle 分页查询SQL
     * @param pageUtil
     * @param sqlBuffer
     * @return
     */
    private String getOraclePageSql(PageUtil pageUtil, StringBuffer sqlBuffer) {
        //oracle 分页使用rownum, 从1开始
        final int offset = (pageUtil.getPageIndex() - 1) * pageUtil.getPageSize() + 1;
        sqlBuffer.insert(0, "select u.* rownum r from (");
        sqlBuffer.append(") u where rownum < ");
        sqlBuffer.append(offset + pageUtil.getPageSize());
        sqlBuffer.insert(0, "select * from (");
        sqlBuffer.append(") where r >= ");
        sqlBuffer.append(offset);
        
        return sqlBuffer.toString();
    }
    
    /**
     * 设置获取总行数SQL
     * @param pageUtil
     * @param mappedStatement
     * @param connection
     */
    private void setTotalRecord(PageUtil pageUtil, MappedStatement mappedStatement, Connection connection) {
        final BoundSql boundSql = mappedStatement.getBoundSql(pageUtil);
        final String sql = boundSql.getSql();
        if (logger.isDebugEnabled()) {
            logger.debug("原始SQL：" + sql);
        }
        final String countSql = this.getCountSql(sql);
        if (logger.isDebugEnabled()) {
            logger.debug("从原始SQL中获取记录总行数SQL：" + countSql);
        }
        final List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        final BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, pageUtil);
        final ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, pageUtil, countBoundSql);
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
        preparedStatement = connection.prepareStatement(countSql);
        parameterHandler.setParameters(preparedStatement);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            final int totalRecord = resultSet.getInt(1);
            if (logger.isDebugEnabled()) {
                logger.debug("总记录数：" + totalRecord);
            }
            pageUtil.setTotal(totalRecord);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取记录总行数SQL
     * @param sql
     * @return
     */
    private String getCountSql(String sql) {
        if (isComplexSQL(sql)) {
            String simpleSql = sql;
            int i = sql.indexOf("order by");
            if (i > 0) {
                simpleSql = sql.substring(0, i);
                if (logger.isDebugEnabled()) {
                    logger.debug("从原始SQL中获取记录总行数SQL前：" + simpleSql);
                }
            }
            return "select count(*) from (" + simpleSql + ") XYZ";  
        } else {
            final int index = sql.toLowerCase().indexOf("from");
            return "select count(*) " + sql.substring(index, sql.length());
        }
    }
    
    /**
     * 是否复杂SQL
     * @param sql
     * @return
     */
    private boolean isComplexSQL(String sql) {
        return true;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }
    
}
