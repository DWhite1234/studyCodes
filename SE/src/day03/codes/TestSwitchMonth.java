package day03.codes;

import java.util.Scanner;

public class TestSwitchMonth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入月份,年份");
        int month = scanner.nextInt();
        int year = scanner.nextInt();

        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println(31);
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println(30);
                break;
            case 2:
                if(year%400==0||year%4==0 && year%100!=0){
                    System.out.println(29);
                }else{
                    System.out.println(28);
                }
                System.out.println(28);
                break;
        }
//        switch (month){
//            case 1:
//                System.out.println(31);
//                break;
//            case 2:
//                if(year%400==0||year%4==0 && year%100!=0){
//                    System.out.println(29);
//                }else{
//                    System.out.println(28);
//                }
//                break;
//            case 3:
//                System.out.println(31);
//                break;
//            case 4:
//                System.out.println(30);
//                break;
//            case 5:
//                System.out.println(31);
//                break;
//            case 6:
//                System.out.println(30);
//                break;
//            case 7:
//                System.out.println(31);
//                break;
//            case 8:
//                System.out.println(31);
//                break;
//            case 9:
//                System.out.println(30);
//                break;
//            case 10:
//                System.out.println(31);
//                break;
//            case 11:
//                System.out.println(30);
//                break;
//            case 12:
//                System.out.println(31);
//                break;
//        }
    }
}
