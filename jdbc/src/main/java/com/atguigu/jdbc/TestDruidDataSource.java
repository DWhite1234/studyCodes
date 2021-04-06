package com.atguigu.jdbc;

import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TestDruidDataSource {
    @Test
    public void test01() throws Exception {
        Connection conn = DataSourceUtils.getConnection();
        System.out.println("conn = " + conn);
        DataSource ds = DataSourceUtils.getDataSource();
    }
}
