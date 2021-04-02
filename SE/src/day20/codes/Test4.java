package day20.codes;

import java.io.*;

public class Test4 {
    public static void main(String[] args) {
        try (
                Reader r = new FileReader("E:/c.txt");
                Writer writer = new FileWriter("E:/d.txt");
        ) {
            char[] ch = new char[10];
            int len;
            while ((len = r.read(ch)) != -1) {
                writer.write(ch, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
