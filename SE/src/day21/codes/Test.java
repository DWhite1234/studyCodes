package day21.codes;

import java.io.*;

public class Test{
    public static void main(String[] args) {
        try (
                InputStream is = new FileInputStream("E:\\图片\\背景\\排球女.jpg");
                BufferedInputStream br = new BufferedInputStream(is);
                OutputStream os = new FileOutputStream("E:\\图片\\1.jpg");
                BufferedOutputStream bos = new BufferedOutputStream(os)
        ) {

            byte by[] = new byte[10];
            int len;
            while ((len = br.read(by)) != -1) {
                bos.write(by, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
