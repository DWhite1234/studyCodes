package day05.homework;

import java.util.Arrays;
import java.util.Scanner;

public class Test11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入本组人数");
        int num=scanner.nextInt();
        int[] score = new int[num];
        for (int i = 0; i < score.length; i++) {
            System.out.println("请输入成绩");
            score[i] = scanner.nextInt();
        }
        for (int i = 0; i < score.length; i++) {
            boolean flag=true;
            for (int j = 0; j < score.length - i - 1; j++) {
                if (score[j] <= score[j + 1]) {
                    int temp = score[j];
                    score[j] = score[j + 1];
                    score[j+1]=temp;
                    flag=false;
                }
            }
            if (flag) {
                break;
            }
        }
        System.out.println(Arrays.toString(score));
    }
}
