package day05.codes;

import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        String [] arr = new String[7];
        arr[0] = "Monday";
        arr[1] = "TuesDay";
        arr[2] = "WxxxDay";
        arr[3] = "ThxxxxDay";
        arr[4] = "FriDay";
        arr[5] = "SaxxxDay";
        arr[6] = "SunDay";
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入1-7之间的数字");
        int i = scanner.nextInt();
        System.out.println(arr[i-1]);
    }
}
