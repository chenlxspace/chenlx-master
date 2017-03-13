package com.chenlx.sm.demo.core;

public class MultipleDataSourceHolder {
    private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();

    public static void setDataSource(String dataSource) {
        dataSourceKey.set(dataSource);
    }
    
    public static String getDataSource() {
        return dataSourceKey.get();
    }
    
    public static void clear() {
        dataSourceKey.remove();
    }
}
