package day21.notes;

import org.junit.Test;

import java.io.*;
import java.util.Properties;

public class TestProperties {
    @Test
    public void test01() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("ch", "china");
        properties.setProperty("ja", "japan");
        properties.setProperty("cc", "chinese");
        properties.store(new FileOutputStream("D:/pro.properties"), "测试");
        properties.store(new FileOutputStream("pro2.properties"), "测试");
    }

    @Test
    public void test02() throws IOException {
        Properties properties =new Properties();
        properties.load(new FileInputStream("pro2.properties"));
        properties.list(new PrintStream("p4.txt"));
        PrintWriter printStream = new PrintWriter(new FileOutputStream("ppp.txt"));
        properties.list(printStream);
        printStream.flush();

    }
}
