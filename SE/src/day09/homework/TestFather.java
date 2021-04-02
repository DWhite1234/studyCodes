package day09.homework;

public class TestFather {
    private String name="张三";
    private static int age=18;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        TestFather.age = age;
    }

    public static void show() {
        System.out.println("父类静态方法");
    }

    public void show2() {
        System.out.println("父类非静态方法");
    }
}
