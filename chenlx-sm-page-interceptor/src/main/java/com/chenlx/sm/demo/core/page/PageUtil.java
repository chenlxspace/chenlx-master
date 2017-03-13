package com.chenlx.sm.demo.core.page;

import java.io.Serializable;

/**
 * 分页工具
 * @author Richard
 * @date 2017年3月13日
 * @time 上午9:44:33
 */
public class PageUtil implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2456783952703794017L;
    
    /**
     * 总页数
     */
    private int total;
    
    /**
     * 起始页
     */
    private int pageIndex;
    
    /**
     * 页容量
     */
    private int pageSize;

    /**
     * 字段集(表映射实体)
     */
    private Object fields;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Object getFields() {
        return fields;
    }

    public void setFields(Object fields) {
        this.fields = fields;
    }

    /**
     * 驼峰命名转下划线命名
     * 
     * @param param
     * @return
     */
    public static String camelToUnderLine(String param) {
        if(param == null || "".equals(param.trim())) {
            return "";
        }
        
        int len = param.length();
        
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append('_');
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
    
}
