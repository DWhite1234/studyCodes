package day21.codes;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Test2 {

    /**
     * 打印流
     *  可以向文件中打印东西
     * @throws
     */
    @Test
    public void test01() throws FileNotFoundException {
        PrintStream printStream = new PrintStream("D:/f.txt");
        printStream.println("66666666666");

        System.setOut(printStream);
        System.out.println("9999");
    }

    /**
     * scanner 可以读取文件
     * @throws FileNotFoundException
     */
    @Test
    public void test02() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("D:/a.txt"));
        while (scanner.hasNext()) {
            String next = scanner.next();
            System.out.println(next);
        }
        scanner.close();
    }

    /*
    测试类中scanner 输入有问题
     */
    @Test
    public void test03() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入数字");
        scanner.next();
    }
}
