package day07.codes;

public class Test1 {
    static int i=0;
    static String name = "张三";
    public static void main(String[] args) {
//        printHello(i);
//        System.out.println("i = " + i);

        test(name);
        System.out.println("name = " + name);
    }

    public static void printHello(int i) {
        i++;
        System.out.println("i = " + i);
    }

    public static void test(String name) {
        name = "李四";
        System.out.println("name = " + name);
    }
}

