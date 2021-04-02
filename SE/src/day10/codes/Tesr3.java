package day10.codes;

public class Tesr3 {
    public static void main(String[] args) {
//        TestSon.show1();
//        TestFather.show1();
//        TestSon.show3();
        TestSon.show1();
//        TestFather testFather = new TestSon();
        TestSon testSon = new TestSon();
        testSon.run();
        testSon.show2();
        System.out.println("testSon.name = " + testSon.name);
    }
}

class TestFather{
    String name = "服了";
    public static void show1(){
        System.out.println("父类静态方法初始化");
    }
    static{
        System.out.println("父类静态代码块初始化");
    }
    public  void show2(){
        System.out.println("父类非静态方法初始化");
    }
    {
        System.out.println("父类代码块初始化");
    }
}

class TestSon extends TestFather {
    public static void show3(){
        System.out.println("子类静态方法初始化");
    }
//    public static void show1(){
//        System.out.println("子类同名静态方法初始化");
//    }
    static{
        System.out.println("子类静态代码块初始化");
    }
    public  void show4(){
        System.out.println("子类非静态方法初始化");
    }
    {
        System.out.println("子类代码块初始化");
    }

    public void run() {
        System.out.println("子类牌");
    }
}
