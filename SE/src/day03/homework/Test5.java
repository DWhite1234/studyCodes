package day03.homework;

import java.util.Scanner;

public class Test5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入年份");

        int year = scanner.nextInt();
        char sx=' ';

        switch (year%12){
            case 1:
                sx = '鸡';
                break;
            case 2:
                sx = '狗';
                break;
            case 3:
                sx = '猪';
                break;
            case 4:
                sx = '鼠';
                break;
            case 5:
                sx = '牛';
                break;
            case 6:
                sx = '虎';
                break;
            case 7:
                sx = '兔';
                break;
            case 8:
                sx = '龙';
                break;
            case 9:
                sx = '蛇';
                break;
            case 10:
                sx = '马';
                break;
            case 11:
                sx = '羊';
                break;
            case 0:
                sx = '猴';
                break;
        }
        System.out.println(year+"年是"+sx+"年");
    }
}
