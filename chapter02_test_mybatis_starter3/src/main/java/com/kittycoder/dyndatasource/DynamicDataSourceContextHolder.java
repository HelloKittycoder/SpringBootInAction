package com.kittycoder.dyndatasource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shucheng on 2020/9/11 20:43
 */
public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
    private static final List<String> dataSourceIds = new ArrayList<>();

    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return contextHolder.get();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    public static boolean containsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }

    public static List<String> getDataSourceIds() {
        return dataSourceIds;
    }
}
