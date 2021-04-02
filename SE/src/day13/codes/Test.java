package day13.codes;

import java.util.InputMismatchException;

public class Test {
    public static void main(String[] args) {
        int show = show();
        System.out.println("show = " + show);
    }

    private static int show() {
        String b = null;
        int a = 10;
        try {
            b.equals("sss");
            System.out.println(a);
            System.out.println("异常后执行");
        } catch ( NullPointerException  e) {
            e.printStackTrace();
            a = 20;
            System.out.println(a);
            return a;
        }catch (  InputMismatchException e) {
            e.printStackTrace();
            a = 20;
            System.out.println(a);
            return a;
        }finally {
            a = 30;
            System.out.println(a);
            return a;
        }
    }
}
