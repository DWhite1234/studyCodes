package day05.codes;

import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        double []score = new double[3];
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入三个成绩,计算总和,平均数");
        for (int i = 0; i < score.length; i++) {
            score[i]=scanner.nextDouble();
        }
        double sum=0;
        for (double d:score) {
            sum+=d;
        }
        System.out.println("总和:" + sum);
        System.out.println("平均数:"+sum/score.length);
    }
}
