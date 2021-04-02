package day20.codes;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        File file = new File("E:\\0225\\java\\day00");
        showDirs(file);

    }

    public static void showDirs(File file) {
        for (File listFile : file.listFiles()) {
            if (listFile.isDirectory()) {
                System.out.println("<DIR>\t\t"+listFile.getName());
                showDirs(listFile);
            } else {
                System.out.println("<File>\t\t"+listFile.getName());
            }
        }
    }
}
