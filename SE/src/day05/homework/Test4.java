package day05.homework;

import java.util.Arrays;

public class Test4 {
    public static void main(String[] args) {
        char[] arr = new char[]{'a', 'l', 'f', 'm', 'f', 'o', 'b', 'b', 's', 'n'};
        int [] arr2=new int[26];
        for (char c : arr) {
            for (char i = 'a'; i <= 'z'; i++) {
                if (c == i) {
                    arr2[(int)(i-97)]++;
                }
            }
        }
        for (int i =0;i<arr2.length;i++) {
            if (arr2[i] > 0) {
                System.out.println((char)(i+97)+"-->"+arr2[i]);
            }
        }
    }
}
