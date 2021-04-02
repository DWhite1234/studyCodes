package day12.codes;

import day13.codes.Test;

public class Test1 {
    String name = "张三";
    static int age = 18;

    class Inner{
        final static String name = "李四";
        int age=20;

        public void showInner() {
            System.out.println("我是内部类");
        }
    }
    public void show() {
        Inner inner = new Test1().new Inner();
        Inner inner1 = new Inner();
        System.out.println("inner = " + inner.age);
        System.out.println("inner = " + inner1.age);
    }

    public static void show2() {
        Inner inner = new Test1().new Inner();
//        Inner inner1 = new Inner();
        System.out.println("inner.name = " + inner.name);
    }

    public static void main(String[] args) {
        new Test1().show();
        Test1.show2();
    }
}
