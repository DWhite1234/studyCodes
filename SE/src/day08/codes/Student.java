package day08.codes;

public class Student {
    String name;
    int score;

    public String getInfo(Student student) {
        return "学生姓名是:" + student.name + "学生成绩是:" + student.score;
    }
}
