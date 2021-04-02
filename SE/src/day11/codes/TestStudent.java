package day11.codes;

import java.util.Arrays;

public class TestStudent {
    public static void main(String[] args) {
        Student[] stu = new Student[]{new Student("张三", 20, 99),
                new Student("李四", 15, 100),
                new Student("王五", 10, 20)};

        sort(stu);
        System.out.println("Arrays.toString(stu) = " + Arrays.toString(stu));
    }

    private static void sort(Comparable[] stu) {
        for (int i = 0; i < stu.length-1; i++) {
            for (int j = 0; j < stu.length - 1 - i; j++) {
                if (stu[j].compareTo(stu[j + 1])>0) {
                    Comparable temp= stu[j+1];
                    stu[j + 1] = stu[j];
                    stu[j]=temp;
                }
            }
        }
    }
}
