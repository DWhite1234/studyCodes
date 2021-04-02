package day17.codes;

import java.util.Arrays;

public class ChooseSort {
    public static void main(String[] args) {
        int arr[] = {10, 50, 20, 70, 1, 33, 60, 99};
        int value=20;
        sort2(arr);
        System.out.println(Arrays.toString(arr));
//        int sort = sort(arr, value);
//        System.out.println("sort = " + sort);
    }

    private static void sort2(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            int midIndex=i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[midIndex] >= arr[j]) {
                    midIndex=j;
                }
            }
            int tem = arr[midIndex];
            arr[midIndex] = arr[i];
            arr[i]=tem;
        }
    }

    private static int sort(int[] arr, int value) {
        Arrays.sort(arr);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        int startIndex=0;
        int endIndex=arr.length-1;
        while (endIndex >= startIndex) {
            int midIndex=(startIndex+endIndex)/2;
            if (arr[midIndex] > value) {
                endIndex=midIndex-1;
            } else if (arr[midIndex] < value) {
                startIndex=midIndex+1;
            }else{
                return midIndex;
            }
        }
        return -1;
    }
}
