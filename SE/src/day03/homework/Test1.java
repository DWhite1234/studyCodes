package day03.homework;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个数");
        int a=scanner.nextInt();

        if(a%2==0){
            System.out.println(a+"是偶数");
        }else{
            System.out.println(a+"是奇数");
        }
    }
}
