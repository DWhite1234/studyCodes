package day03.homework;

import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个字符");
        char c = scanner.next().charAt(0);
        if(c>=48&&c<57){
            System.out.println(c+"是一个数字");
        }else if(c>=65&&c<=122){
            System.out.println(c+"是一个字符");
        }else{
            System.out.println(c+"是其他字符");
        }
    }
}
