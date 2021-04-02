package day03.homework;

import java.util.Scanner;

public class Test6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入方程的三个参数");

        double a=scanner.nextDouble();
        double b=scanner.nextDouble();
        double c=scanner.nextDouble();
        double result = b*b-4*a*c;
        double x1=0.0;
        double x2=0.0;
        if (result>=0 ){
            if(a==0&&b!=0){
                x1=-c/b;
                System.out.println("x="+x1);
            }else if(a==0&& b==0){
                System.out.println("方程无解");
            }else{
                if (result > 0) {
                    x1 = (-b + Math.sqrt(result)) / 2 * a;
                    x2 = (-b - Math.sqrt(result)) / 2 * a;
                    System.out.println("x1=" + x1);
                    System.out.println("x2=" + x2);
                } else if (result == 0) {
                    x1 = x2 = -b / 2 * a;
                    System.out.println("x=" + x1);
                }
            }
        }else {
            System.out.println("方程无解");
        }

    }
}
