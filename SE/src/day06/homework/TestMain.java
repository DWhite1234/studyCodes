package day06.homework;

public class TestMain {
    public static void main(String[] args) {
        Employee e1 = new Employee();
        Employee e2 = new Employee();
        e1.name="张三";
        e1.age = 18;
        e1.no = "999999";
        e1.salary = 55.55;

        e2.name="李四";
        e2.age = 20;
        e2.no = "66666";
        e2.salary = 155.55;


        System.out.println("员工1的编号:"+e1.no+",姓名:"+e1.name+",年龄:"+e1.age+",薪资:"+e1.salary);
        System.out.println("员工1的编号:"+e2.no+",姓名:"+e2.name+",年龄:"+e2.age+",薪资:"+e2.salary);
    }
}
