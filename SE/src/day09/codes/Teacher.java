package day09.codes;

public class Teacher extends Person {
    private double salary;

    public Teacher(String name, int age, char sex, double salary) {
        super(name, age, sex);
        this.salary = salary;
    }

    public Teacher() {
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getInfo() {
        return super.getInfo()+"薪资:"+salary;
    }


}
