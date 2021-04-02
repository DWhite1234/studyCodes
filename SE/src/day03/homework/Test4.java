package day03.homework;

import java.util.Scanner;

public class Test4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入生日月份");
        int birthMonth = scanner.nextInt();
        System.out.println("请输入生日日期");
        int birthDay= scanner.nextInt();
        String str = "";
        switch (birthMonth){
            case 1:
                if(birthDay<20){
                    str = "摩羯座";
                }else{
                    str = "水瓶座";
                }
                break;
            case 2:
                if(birthDay<19){
                    str = "水瓶座";
                }else{
                    str = "双鱼座";
                }
                break;
            case 3:
                if(birthDay<21){
                    str = "双鱼座";
                }else{
                    str = "白羊座";
                }
                break;
            case 4:
                if(birthDay<20){
                    str = "白羊座";
                }else{
                    str = "金牛座";
                }
                break;
            case 5:
                if(birthDay<21){
                    str = "金牛座";
                }else{
                    str = "双子座";
                }
                break;
            case 6:
                if(birthDay<22){
                    str = "双子座";
                }else{
                    str = "巨蟹座";
                }
                break;
            case 7:
                if(birthDay<23){
                    str = "巨蟹座";
                }else{
                    str = "狮子座";
                }
                break;
            case 8:
                if(birthDay<23){
                    str = "狮子座";
                }else{
                    str = "处女座";
                }
                break;
            case 9:
                if(birthDay<23){
                    str = "处女座";
                }else{
                    str = "天秤座";
                }
                break;
            case 10:
                if(birthDay<24){
                    str = "天秤座";
                }else{
                    str = "天蝎座";
                }
                break;
            case 11:
                if(birthDay<23){
                    str = "天蝎座";
                }else{
                    str = "射手座";
                }
                break;
            case 12:
                if(birthDay<22){
                    str = "射手座";
                }else{
                    str = "水瓶座";
                }
                break;
        }
        System.out.println("生日"+birthMonth+"月"+birthDay+"日是"+str);
    }
}
