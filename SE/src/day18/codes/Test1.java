package day18.codes;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Test1 {

    @Test
    public void test01() {
        int arr[] = {10, 20, 30, 40};
        int arr2[] = {10, 20, 30, 40};

        List<int[]> ints = Arrays.asList(arr);
        ints.add(arr2);
//        List<Integer> list = Arrays.asList(10, 20, 30, 40, 50);
//        System.out.println(list);
//        list.add(new Integer(60));

    }
}
