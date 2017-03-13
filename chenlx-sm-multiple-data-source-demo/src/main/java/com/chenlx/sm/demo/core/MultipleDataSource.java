package com.chenlx.sm.demo.core;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 继承AbstractRoutingDataSource 实现支持多数据源
 * @author Richard
 * @date 2017年3月12日
 * @time 下午5:20:54
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
    
    @Override
    protected Object determineCurrentLookupKey() {
        return MultipleDataSourceHolder.getDataSource();
    }

}
