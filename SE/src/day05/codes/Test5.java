package day05.codes;

import java.util.Arrays;

public class Test5 {
    public static void main(String[] args) {
        int[] arr = {70, 60, 50, 40, 30, 20, 10};

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j <arr.length - i - 1; j++) {
                if (arr[j] >= arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j+1]=temp;
                }
                System.out.print(arr[j]+" ");
            }
            System.out.println();
        }
        System.out.println(Arrays.toString(arr));
    }
}
