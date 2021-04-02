package day16.homework;

import java.util.Arrays;

/*
有一个数组a[1000]存放0-999，要求每隔二个数删掉一个数，到末尾时循环至开头，继续运行。求最后一个被删掉的数的原始下标位置。

以8个数为例：

{0,1,2,3,4,5,6,7}：0->1->2（删掉）->3->4->5（删掉）->6->7->0（删掉）。。。


 */
public class Test1 {
    public static void main(String[] args) {
        int arr[] = new int[100];
        int[] arr2 = new int[100];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        System.arraycopy(arr, 0, arr2, 0, arr.length);
        int result = getResult(arr);
        System.out.println("result = " + result);
        System.out.println("最后删除的一个数为:" + result);
        for (int i = 0; i < arr2.length; i++) {
            if (result == arr2[i]) {
                System.out.println("下标为:" + i);
                break;
            }
        }

    }

    private static int getResult(int[] arr) {
        int length = arr.length;
        int result = 0;
        int count = 0;
        while (length > 0) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != -1) {
                    count++;
                }
                if (arr[i] != -1 && count % 3 == 0) {
                    length--;
                    result = arr[i];
                    arr[i] = -1;
                }
            }
            System.out.println("c" + count);
            System.out.println("l" + length);
        }
        return result;
    }
}
