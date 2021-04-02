package day09.homework;

public class Test11 {
    static int x, y, z;

    static {
        int x = 5;
        x--;
    }

    static {
        x--;
    }

    public static void main(String[] args) {
        System.out.println("x=" + x);//3
        z--;//-1
        method();//0+1
        System.out.println("result:" + (z + y + ++z));//1+0+2
    }

    public static void method() {
        y = z++ + ++z;//-1+1
    }
}