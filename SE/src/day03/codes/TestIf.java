package day03.codes;

import java.util.Scanner;

public class TestIf {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        System.out.println("请输入你的成绩!");
        int grade = scanner.nextInt();

        String level="";
        if(grade>=90&&grade<=100){
            level = "A";
        }else if(grade>=80&&grade<=89){
            level = "B";
        }else if(grade>=70&&grade<=79){
            level = "C";
        }else if (grade>=60&&grade<=69){
            level = "D";
        }else if(grade<60){
            level = "E";
        }else{
            level="你输入的成绩不正确";
        }
        System.out.println("你的成绩等级是:"+level);
    }
}
