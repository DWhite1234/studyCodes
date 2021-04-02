package day04.codes;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count1=0;
        int count2=0;
        System.out.println("请输入n个数");
        boolean flag=true;
        while(flag){
            int a=scanner.nextInt();
            if(a>0){
                count1++;
            }else if(a<0){
                count2++;
            }else{
                flag=!flag;
            }
        }
        System.out.println("正数:"+count1);
        System.out.println("负数:"+count2);
    }
}
