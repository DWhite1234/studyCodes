package com.atguigu.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static Properties p = new Properties();
    //加载配置文件内容

    /**
     * 加载当前模块下的配置文件
     */
    static {
        //属性集自带的加载方式
        System.out.println("----------->属性集自带的加载方式");
        try {
            p.load(new FileInputStream(new File("db.properties")));
            p.list(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 加载当面根目录或者resource资源文件下的文件
     */
    static {
        //加载器加载
        System.out.println("------------------>当前线程加载器加载");


        try (
                InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
        ) {
            if (stream == null) {
                throw new RuntimeException("配置文件加载失败");
            }
            p.load(stream);
            p.list(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        //获取三元素
        String url = p.getProperty("jdbc.url");
        String driver = p.getProperty("jdbc.driver");
        String userName = p.getProperty("jdbc.userName");
        String password = p.getProperty("jdbc.password");
        //加载驱动
        Class.forName(driver);
        //返回创建链接
        return DriverManager.getConnection(url, userName, password);
    }
}
