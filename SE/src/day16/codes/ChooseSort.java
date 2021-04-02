package day16.codes;

import java.util.Arrays;

public class ChooseSort {
    public static void main(String[] args) {
        int arr[] = {20, 10, 50, 66, 5, 88, 99,500,400,100,55,800,100,77,100,555,615,154,587};
        long l1 = System.currentTimeMillis();
        System.out.println();
//        sort(arr);
//        sor2(arr);
//        sort3(arr);
        long l2 = System.currentTimeMillis();
        System.out.println(l2-l1);
    }
    //冒泡排序
    private static void sort3(int[] arr) {
        for(int i = 0;i<arr.length-1;i++){
            //假设此次排序已经有序
            boolean flag = true;//优化
            for(int j = 0;j<arr.length-1-i;j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;

                    flag = false;//意义:只要进入该条件就代表还未全排序
                }
            }
            //全排序后直接退出循环
            if(flag){
                break;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    //选择排序结合冒泡
    private static void sor2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length - 1; j++) {
                if (arr[j+1] < arr[i]) {
                    minIndex = j+1;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    //选择排序
    private static void sort(int arr[]) {
        //选择排序
        //每次挑选数组中最小的值,放在数组首位,依次排序
        //假设数组最小的值的下标为0

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] >= arr[j]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }
}
