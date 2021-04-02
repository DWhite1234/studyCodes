package day09.homework;

public class TestSon extends TestFather {
    public static void show() {
        System.out.println("子类静态方法");
    }
    @Override
    public void show2() {
        System.out.println("子类非静态方法");
    }
}
