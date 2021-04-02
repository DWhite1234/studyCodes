package day20.codes;

import java.io.*;

public class Test2 {
    public static void main(String[] args) throws IOException {
        File file = new File("E:\\图片\\背景\\排球女.jpg");
        InputStream is = new FileInputStream(file);
        OutputStream os = new FileOutputStream("E:\\图片\\美女.jpg",true);
        //读取图片
        byte[] bs = new byte[10];
        int len = is.read(bs);
        while (len != -1) {
            os.write(bs);
            len = is.read(bs);
        }
        os.close();
        is.close();
    }
}
