package day05.codes;

import java.util.Arrays;

public class Test7 {
    public static void main(String[] args) {
        int [][]arr=new int[5][];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=new int[i+1];
            for (int j = 0; j <arr[i].length;j++) {
                arr[i][j]=i+1;
            }
            System.out.println(Arrays.toString(arr[i]));
        }
    }
}
