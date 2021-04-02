package day18.codes;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Test {
    public static void main(String[] args) {

        //2-100质数,添加到list
        Collection list = new ArrayList<>();

        l: for (int i = 2; i <= 100; i++) {
            for (int j = 2; j < i; j++) {
                if (i%j==0) {
                    continue l;
                }
            }
            list.add(i);
        }
        System.out.println(list);
    }
}
