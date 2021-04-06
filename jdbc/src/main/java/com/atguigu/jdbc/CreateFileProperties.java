package com.atguigu.jdbc;

import java.io.*;
import java.util.Properties;

public class CreateFileProperties {
    private static File file=new File("db.properties");
    private static Properties p = new Properties();
    public static void createProperties() throws IOException {
        if (!file.exists()) {
            file.createNewFile();

            p.setProperty("jdbc.driver", "com.mysql.jdbc.Driver");
            p.setProperty("jdbc.url", "jdbc:mysql://localhost:3306/mydb?useUnicode=true&characterEncoding=utf8&useSSL=false");
            p.setProperty("jdbc.userName", "root");
            p.setProperty("jdbc.password","123456");
            //将配置文件的属性写入配置文件中
            p.store(new BufferedOutputStream(new FileOutputStream(file)), "测试配置文件");
            p.list(System.out);
        }
    }
}
