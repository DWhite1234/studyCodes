package day05.homework;

import java.util.Arrays;
import java.util.Scanner;

public class Test9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入,组员人数");
        int num = scanner.nextInt();
        String[] group = new String[num];
        System.out.println("请输入,组员各个姓名");
        for (int i = 0; i < group.length; i++) {
            group[i]=scanner.next();
        }
        System.out.println("请输入要查找的姓名");
        String name= scanner.next();
        for (int i=0;i<group.length;i++) {
            if (group[i].equals(name) ) {
                System.out.println(i);
                break;
            }
        }
    }
}
