package day20.codes;

import java.io.*;

public class Test3 {
    public static void main(String[] args) {
        Reader r=null;
        Writer writer=null;
        try {
            r = new FileReader("E:/c.txt");
            writer = new FileWriter("E:/d.txt");
            char[] ch = new char[10];
            int len;
            while ((len = r.read(ch)) != -1) {
                writer.write(ch, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (r != null) {
                    r.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
