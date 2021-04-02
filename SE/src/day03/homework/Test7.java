package day03.homework;

import java.util.Scanner;

public class Test7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入月份");
        int month = scanner.nextInt();
        System.out.println("请输入日期");
        int day = scanner.nextInt();
        int year = 2019;
        int totalNoW=0;
        //先统计一年的每月天数
        switch (month-1){
            case 11:
                totalNoW+=30;
            case 10:
                totalNoW+=31;
            case 9:
                totalNoW+=30;
            case 8:
                totalNoW+=31;
            case 7:
                totalNoW+=31;
            case 6:
                totalNoW+=30;
            case 5:
                totalNoW+=31;
            case 4:
                totalNoW+=30;
            case 3:
                totalNoW+=31;
            case 2:
                totalNoW+=28;
            case 1:
                totalNoW+=31;
            case 0:
                totalNoW+=day;
                break;
        }
        System.out.println(month+"月"+day+"日,是今年第"+totalNoW+"天");
        char date=' ';
        switch (totalNoW%7){
            case 0:
                date = '一';
                break;
            case 1:
                date = '二';
                break;
            case 2:
                date = '三';
                break;
            case 3:
                date = '四';
                break;
            case 4:
                date = '五';
                break;
            case 5:
                date = '六';
                break;
            case 6:
                date = '日';
                break;
        }
        System.out.println(month+"月"+day+"日,是星期"+date);
    }
}
