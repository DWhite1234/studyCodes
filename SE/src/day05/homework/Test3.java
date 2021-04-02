package day05.homework;

import java.util.Arrays;

public class Test3 {
    public static void main(String[] args) {
        String [] arr1=new String[]{"黑桃", "红桃", "梅花", "方块"};
        String[] arr2 = new String[]{"1", "2", "3", "4", "5", "6", "7", "8","9", "10", "J", "Q", "K"};
        String []arr3=new String[54];
        int count=0;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                arr3[count++]=arr1[i]+arr2[j];
            }
        }
        arr3[count]="大王";
        arr3[++count]="小王";
        System.out.println(arr3[0]+"\t"+arr3[4]+"\t"+arr3[49]);
    }
}
