package com.kittycoder;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

/**
 * Created by shucheng on 2020/9/11 19:39
 */
public class BaseTest {

    /**
     * 指定某个数据源执行某个指定的sql脚本（使用spring-jdbc自带的工具类）
     * @param dataSource
     * @param scriptLocation "sql/h2_createTable.sql"
     * @throws Exception
     */
    protected static void executeSql(DataSource dataSource, String scriptLocation) throws Exception {
        // String scriptLocation = "sql/h2_createTable.sql";

        Resource resource = new ClassPathResource(scriptLocation);
        ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);
    }
}
