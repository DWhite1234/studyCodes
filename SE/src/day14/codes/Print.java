package day14.codes;

import java.io.PrintStream;

import static sun.misc.Version.print;

public class Print extends Thread {
    private static int num=1;

    public Print(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            printStream();
        }
    }

    private static synchronized void printStream() {
        if (num % 3 == 0) {
            try {
                Thread.currentThread().getName().wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
