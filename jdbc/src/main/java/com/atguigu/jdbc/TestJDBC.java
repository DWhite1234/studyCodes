package com.atguigu.jdbc;

import com.atguigu.jdbc.entity.User;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class TestJDBC {
    /**
     * 测试创建配置文件
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        CreateFileProperties.createProperties();
    }

    /**
     * 测试加载配置文件
     */
    @Test
    public void test02() {
        JDBCUtils jdbcUtils = new JDBCUtils();
    }

    /**
     * 测试获取连接
     */
    @Test
    public void test03() throws SQLException, ClassNotFoundException {
        Connection conn = JDBCUtils.getConnection();
        System.out.println("conn = " + conn);

    }

    /**
     * JDBCUtils使用select *
     */
    @Test
    public void test04() throws SQLException, ClassNotFoundException {
        Connection conn = JDBCUtils.getConnection();
        //编写sql
        String sql = "select * from testJDBC";
        //SQL执行对象
        PreparedStatement ps = conn.prepareStatement(sql);
        //executeQuery执行查询sql,返回结果集
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            User use=new User();
            use.setId(rs.getInt(1));
            use.setUrl(rs.getString(2));
            use.setUsername(rs.getString(3));
            use.setPwd(rs.getString(4));
            System.out.println(use);
        }
    }

    /**
     * JDBCUtils测试使用DML
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void test05() throws SQLException, ClassNotFoundException {
        Connection conn = JDBCUtils.getConnection();
        //编写sql,使用?占位,防止硬编码
        String sql = "update testJDBC set pwd=? where id=?";
        //SQL执行对象
        PreparedStatement ps = conn.prepareStatement(sql);
        //给占位符赋值
        ps.setString(1, "123");
        ps.setInt(2, 3);
        //executeUpdate执行所有的DML语句(update,delete,insert),
        int rows = ps.executeUpdate();
        System.out.println("rows = " + rows);
    }

    /**
     * 测试使用批处理和事务
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void test06() throws SQLException, ClassNotFoundException {
        Connection conn = JDBCUtils.getConnection();
        //编写sql,使用?占位,防止硬编码
        String sql = "insert into testJDBC(url,username,pwd) values(?,?,?)";
        //SQL执行对象
        PreparedStatement ps = conn.prepareStatement(sql);
        try {
            //开启事务,关闭默认提交
            conn.setAutoCommit(false);
            //给占位符赋值
            for (int i = 30; i < 40; i++) {
                ps.setString(1, i + "1");
                ps.setString(2, i + "2");
                ps.setString(3, i + "3");
                ps.addBatch();
            }
            //执行批处理
            int[] rows = ps.executeBatch();
            conn.commit();
            System.out.println(1/0);
            System.out.println("rows = " + Arrays.toString(rows));
        } catch (Exception e) {
            //失败回滚
            conn.rollback();
            e.printStackTrace();
            throw new RuntimeException("批处理失败");
        }


    }
}
