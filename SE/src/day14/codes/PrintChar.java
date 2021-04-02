package day14.codes;

public class PrintChar extends Thread {
    private int num;

    public void setStr(int str) {
        this.num = str;
    }

    public PrintChar(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            print();
        }
    }

    private static synchronized void print() {

    }
}
