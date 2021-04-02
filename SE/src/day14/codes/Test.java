package day14.codes;

public class Test {
    public static void main(String[] args) {
//        Rabbit rabbit = new Rabbit();
//        rabbit.start();

//        Rabbit2 rabbit2 = new Rabbit2();
//        Thread thread = new Thread(rabbit2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    System.out.println("兔子牌.....");
                }
            }
        }).start();
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        while (true) {
            System.err.println("乌龟跑.....");
        }
    }
}

class Rabbit extends Thread {
    @Override
    public void run() {
        while (true) {

            System.out.println("兔子牌.....");
        }
    }
}

class Rabbit2 implements Runnable {

    @Override
    public void run() {
        while (true) {

            System.out.println("兔子牌.....");
        }
    }
}
