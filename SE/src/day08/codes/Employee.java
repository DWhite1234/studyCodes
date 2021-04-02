package day08.codes;

public class Employee {
    private String no;
    private String name;
    private double salary;
    private char sex;

    public Employee() {
    }

    public Employee(String no, String name, double salary, char sex) {
        this.no = no;
        this.name = name;
        setSalary(salary);
        setSex(sex);
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary >= 10000 && salary <= 20000) {
            this.salary = salary;
        }else{
            System.out.println("您的薪资水平不正常");
        }

    }
    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        if (sex == '男' || sex == '女') {
            this.sex = sex;
        }else{
            System.out.println("请输入男或女");
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", sex=" + sex +
                '}';
    }

    public void getInfo() {
        System.out.println("toString() = " + toString());
    }
}
