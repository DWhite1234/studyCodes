package com.atguigu.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceUtils {
    private static Properties p = new Properties();
    private static DataSource ds = null;

    static {
        try (
                InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("druid.properties");
        ) {
            p.load(stream);
            //创建连接池对象
            ds = DruidDataSourceFactory.createDataSource(p);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回连接池对象
     * @return
     * @throws Exception
     */
    public static DataSource getDataSource() throws Exception {
        return ds;
    }

    /**
     * druid 连接池创建链接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
