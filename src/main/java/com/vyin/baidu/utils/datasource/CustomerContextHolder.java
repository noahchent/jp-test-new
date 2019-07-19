package com.vyin.baidu.utils.datasource;

public class CustomerContextHolder {
    public static final String DATASOURCE_DATASOURCE = "dataSource";
    public static final String DATASOURCE_H2DATASOURCE = "h2DataSource";

    public static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }

    public static String getCustomerType() {
        return contextHolder.get();
    }

    public static void clearCustomerType() {
        contextHolder.remove();
    }
}
