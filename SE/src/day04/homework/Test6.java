package day04.homework;

import java.util.Scanner;

public class Test6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入年,月,日");
        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int day = scanner.nextInt();
        int total = 0;
        for (int i = 1; i <= month; i++) {
            switch (i-1) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    total += 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    total += 30;
                    break;
                case 2:
                    if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {
                        total += 29;
                    } else {
                        total += 28;
                    }
                    break;
            }
        }
        total+=day;
        System.out.println(year+"年"+month+"月"+day+"日,是今年的第"+total+"天");

        switch (total % 5) {
            case 1:
            case 2:
            case 3:
                System.out.println("大鱼");
                break;
            case 4:
            case 0:
                System.out.println("筛网");
                break;
        }
    }
}
