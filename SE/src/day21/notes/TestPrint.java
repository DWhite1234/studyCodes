package day21.notes;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class TestPrint {

    @Test
    public void test01() throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream("D:/ps.txt"));
        ps.println("打印流111");
    }

    @Test
    public void test02() throws FileNotFoundException {
        PrintStream ps = new PrintStream("D:/ps2.txt");
    }
}
