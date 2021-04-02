package day14.codes;

public class TestRunable implements Runnable {
     int num = 100;

    @Override
    public void run() {
        while (num > 0) {
            sale();
            //同步代码块
//            synchronized (this) {
//                if (num <= 0) {
//                    return;
//                }
//                System.out.println(Thread.currentThread().getName() + "售出" + num + "号票,余票:" + --num);
//            }
        }
    }

    //同步方法
    private synchronized void sale() {
        if (num <= 0) {
            return;
        }
        System.out.println(Thread.currentThread().getName() + "售出" + num + "号票,余票:" + --num);
    }

}

class Test3 {
    public static void main(String[] args) {
        TestRunable testRunable = new TestRunable();
        Thread f1 = new Thread(testRunable, "一号窗口");
        Thread f2 = new Thread(testRunable, "二号窗口");
        Thread f3 = new Thread(testRunable, "三号窗口");
        Thread f4 = new Thread(testRunable, "四号窗口");

        f1.start();
        f2.start();
        f3.start();
        f4.start();
    }
}
