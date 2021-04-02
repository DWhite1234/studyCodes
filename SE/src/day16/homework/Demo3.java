package day16.homework;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Demo3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i;
            }
            System.out.println(delete(arr));
        }
        scanner.close();
    }

    public static int delete (int[] arr) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        while (queue.size() != 1) {
            int count = 0;
            while (count != 2) {
                queue.add(queue.peek());
                queue.poll();
                count++;
            }
            queue.poll();
        }
        return queue.element();
    }
}