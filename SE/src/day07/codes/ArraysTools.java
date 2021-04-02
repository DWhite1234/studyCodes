package day07.codes;

import javax.swing.*;
import java.util.Arrays;

public class ArraysTools {

    public static int[] paixu(int []arr) {
        for (int i = 0; i < arr.length-1; i++) {
            boolean flag=true;
            for (int j = 0; j < arr.length-i-1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    flag=false;
                }
            }
            if (flag){
                break;
            }
        }
        return arr;
    }

    public static void bianli(int[] arr) {
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }
}
