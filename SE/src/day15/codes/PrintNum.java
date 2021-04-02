package day15.codes;

public class PrintNum extends Thread {
    Print print;

    public PrintNum(String name,Print print) {
        super(name);
        this.print=print;
    }

    @Override
    public void run() {
        print.printNum();
    }
}
