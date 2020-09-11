package com.kittycoder;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

import javax.sql.DataSource;
import java.io.Reader;
import java.sql.Connection;

/**
 * Created by shucheng on 2020/9/11 19:39
 */
public class BaseTest {

    /**
     * 指定某个数据源执行某个指定的sql脚本
     * @param dataSource
     * @param scriptLocation "sql/h2_createTable.sql"
     * @throws Exception
     */
    protected static void executeSql(DataSource dataSource, String scriptLocation) throws Exception {
        // String scriptLocation = "sql/h2_createTable.sql";
        Reader reader = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            reader = Resources.getResourceAsReader(scriptLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ScriptRunner runner = new ScriptRunner(conn);
        runner.setAutoCommit(false);
        runner.setStopOnError(false);

        runner.setLogWriter(null);
        runner.setErrorLogWriter(null);

        runner.runScript(reader);
        conn.commit();
        reader.close();
    }
}
