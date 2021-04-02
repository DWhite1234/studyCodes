package day05.homework;

import java.util.Arrays;

public class Test12 {
    public static void main(String[] args) {
        char[] c = new char[62];
        for (int i = 0; i < 26; i++) {
            c[i] = (char) (i + 65);
        }
        for (int i = 0; i < 26; i++) {
            c[i+26] = (char) (i + 97);
        }
        for (int i = 0; i < 10; i++) {
            c[i+52]=(char) (i+48);
        }
        String str="";
        for (int i = 0; i < 6; i++) {
            str += c[(int)(Math.random() * c.length)];
        }
        System.out.println(str);
    }
}
