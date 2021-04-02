package day08.codes;

import java.util.Arrays;

public class TestStudent {
    public static void main(String[] args) {
        Student[] stu = new Student[3];
        stu[0] = new Student();
        stu[0].name = "张三";
        stu[0].score = 55;

        stu[1] = new Student();
        stu[1].name = "李四";
        stu[1].score = 33;

        stu[2] = new Student();
        stu[2].name = "王五";
        stu[2].score = 90;

        for (int i = 0; i < stu.length-1; i++) {
            for (int j = 0; j < stu.length - i - 1; j++) {
                if (stu[j].score < stu[j + 1].score) {
                    Student s = stu[j + 1];
                    stu[j + 1] = stu[j];
                    stu[j] = s;
                }
            }
        }
        for (Student student : stu) {
            System.out.println("student.getInfo(student) = " + student.getInfo(student));
        }
    }
}
