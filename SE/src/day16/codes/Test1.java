package day16.codes;

import java.util.Arrays;

public class Test1 {
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8};
        for (int i = 0; i <=arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[7 - i];
            arr[7 - i] = temp;
        }
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }
}
