package day14.codes;

import org.junit.Test;

public class TestThread {

    public static void main(String[] args) {
        ExtendThread f1 = new ExtendThread("一号窗口");
        ExtendThread f2 = new ExtendThread("二号窗口");
        ExtendThread f3 = new ExtendThread("三号窗口");
        ExtendThread f4 = new ExtendThread("四号窗口");

        f1.start();
        f2.start();
        f3.start();
        f4.start();
    }
}

class ExtendThread extends Thread {
    public ExtendThread(String name) {
        super(name);
    }

    static Object obj = new Object();
    static int num = 100;

    @Override
    public void run() {
        //同步代码块
        while (num>0) {
            sale();
//            synchronized (obj) {
//                if (num <=0) {
//                    return;
//                }
//                System.out.println(Thread.currentThread().getName() + "售出" + num + "号票,余票:" + --num);
//            }
        }
    }

    //同步方法
    private synchronized static void sale() {
        if (num <=0) {
            return;
        }
        System.out.println(Thread.currentThread().getName() + "售出" + num + "号票,余票:" + --num);
    }


}
