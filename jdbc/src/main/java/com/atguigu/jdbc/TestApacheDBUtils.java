package com.atguigu.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.DruidDataSourceUtils;
import com.atguigu.jdbc.entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.List;

/**
 * 测试ApacheDBUtils进行sql执行
 *  DML -->update
 *  DQL -->query
 */
public class TestApacheDBUtils {


    /**
     * 测试 update方法
     */
    @Test
    public void test() throws Exception {
        DataSource ds = DataSourceUtils.getDataSource();
        //sql
        String sql = "delete from testJDBC where id = ?";
        QueryRunner qr = new QueryRunner(ds);
        int rows = qr.update(sql, 12);
        System.out.println("rows = " + rows);
    }

    /**
     * 测试单个查询,结果集为单行多列
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {
        DataSource ds = DataSourceUtils.getDataSource();
        String sql = "select * from testJDBC where id =?";
        QueryRunner qr = new QueryRunner(ds);
        //创建结果集
        BeanHandler<User> bean = new BeanHandler<>(User.class);
        User query = qr.query(sql, bean,25);
        System.out.println("query = " + query);
    }

    /**
     * 测试多个查询,结果集为多行多列
     * @throws Exception
     */
    @Test
    public void test03() throws Exception {
        DataSource ds = DataSourceUtils.getDataSource();
        String sql = "select * from testJDBC";
        QueryRunner qr = new QueryRunner(ds);
        //创建结果集
        BeanListHandler<User> list = new BeanListHandler<>(User.class);
        List<User> rows = qr.query(sql, list);
        rows.forEach(System.out::println);
    }

    /**
     * 测试查询,单行单列
     * @throws Exception
     */
    @Test
    public void test04() throws Exception {
        DataSource ds = DataSourceUtils.getDataSource();
        String sql = "select count(1) from testJDBC";
        QueryRunner qr = new QueryRunner(ds);
        //创建结果集
        ScalarHandler<Long> result = new ScalarHandler<>();
        Long l = qr.query(sql, result);
        System.out.println("l = " + l);
    }

    /**
     * 测试批量插入
     * @throws Exception
     */
    @Test
    public void test05() throws Exception {
        DataSource ds = DataSourceUtils.getDataSource();
        String sql = "insert into testJDBC(url,username,pwd) values(?,?,?)";
        QueryRunner qr = new QueryRunner(ds);
        Object[][] param = new Object[10][3];
        for (int i = 0; i < param.length; i++) {
            for (int j = 0; j < param[i].length; j++) {
                param[i][j]=i+"-"+j;
            }
        }
        int[] rows = qr.batch(sql, param);
    }
}
