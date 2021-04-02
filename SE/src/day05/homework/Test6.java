package day05.homework;

import java.util.Arrays;

public class Test6 {
    public static void main(String[] args) {
        int [] arr=new int[]{1,2,3,4,3,2,1,};
        int [] arr2=new int[]{1,2,5,4,3,2,1,};
        boolean flag=true;
        for (int i = 0; i < arr.length/2; i++) {
            if (arr[i] != arr[arr.length - 1 - i]) {
                flag=false;
                break;
            }
        }
        System.out.println(Arrays.toString(arr)+"数组是否对称"+flag);
    }
}
