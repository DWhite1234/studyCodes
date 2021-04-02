package day09.homework;

public class Test16 {
    public static void main(String[] args) {
        System.out.println("new programmer().getInfo() = " + new programmer().getInfo());
        System.out.println("new designer().getInfo() = " + new designer().getInfo());
        System.out.println("new architect().getInfo() = " + new architect().getInfo());
    }
}
class Employee{
    private String no;
    private String name;
    private int age;
    private double salary;

    public String getInfo() {
        return "员工编号为:"+no+"\t员工姓名为:"+name+"\t员工年龄为:"+age+"\t员工薪资为"+salary;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee() {
    }

    public Employee(String no, String name, int age, double salary) {
        this.no = no;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}
class programmer extends Employee{
    private String language = "java";
    @Override
    public String getInfo() {
        return super.getInfo()+"编程语言为:"+language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public programmer() {
    }

    public programmer(String language) {
        this.language = language;
    }
}
class designer extends Employee{
    private double reward;

    @Override
    public String getInfo() {
        return super.getInfo()+"设计师奖金为:"+reward;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public designer() {
    }

    public designer(double reward) {
        this.reward = reward;
    }
}
class architect extends Employee{
    private int num;

    @Override
    public String getInfo() {
        return super.getInfo()+"架构师股票数量为:"+num;
    }

    public architect() {
    }

    public architect(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
