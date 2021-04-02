package day05.homework;

import java.util.Scanner;

public class Test10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个英文单词");
        String str= scanner.next();
        char [] c=str.toCharArray();
        boolean flag=false;
        for (char cc : c) {
            if ('a' == cc) {
                flag=true;
            }
        }
        System.out.println("是否存在字母a?"+flag);
    }
}
