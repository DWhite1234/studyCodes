package day09.codes;

public class Student extends Person {
    private double score;

    public String getInfo() {
        return super.getInfo() + "成绩:" + score;
    }
    public Student() {
    }

    public Student(String name, int age, char sex, double score) {
        super(name, age, sex);
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
