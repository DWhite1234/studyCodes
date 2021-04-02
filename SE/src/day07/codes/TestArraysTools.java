package day07.codes;

import java.util.Arrays;

public class TestArraysTools {
    public static void main(String[] args) {
        int[] arr=ArraysTools.paixu(new int[]{50,30,50,70,10,90});
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));

        ArraysTools.bianli(new int[]{50,30,50,70,10,90});
    }
}
