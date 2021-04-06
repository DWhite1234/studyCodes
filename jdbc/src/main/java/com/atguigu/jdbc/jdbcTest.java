package com.atguigu.jdbc;

import com.atguigu.jdbc.entity.User;
import org.junit.Test;

import java.sql.*;

public class jdbcTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //创建三元素
        String url = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&characterEncoding=utf8&useSSL=false";
        String userName = "root";
        String password = "123456";
        //创建链接
        Connection conn = DriverManager.getConnection(url, userName, password);

        //编写sql
        String sql = "select * from testJDBC";
        //SQL执行对象
        PreparedStatement ps = conn.prepareStatement(sql);
        //执行sql,返回结果集
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            User use=new User();
            use.setId(rs.getInt(1));
            use.setUrl(rs.getString(2));
            use.setUsername(rs.getString(3));
            use.setPwd(rs.getString(4));
            System.out.println(use);
        }

        //关闭资源
        rs.close();
        ps.close();
        conn.close();
    }
}
