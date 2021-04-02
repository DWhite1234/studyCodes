package day05.homework;

import java.util.Arrays;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择1.随机产生;2.手动录入");
        int choose = scanner.nextInt();
        int[] arr = new int[10];
        if (choose == 1) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = scanner.nextInt();
            }
        } else if (choose == 2) {
            int result = 0;
            for (int i = 0; i < arr.length; i++) {
                arr[i]=(int)(Math.random()*100-1);
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
