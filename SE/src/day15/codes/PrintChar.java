package day15.codes;

public class PrintChar extends Thread {
    Print print;
    public PrintChar(String name,Print print) {
        super(name);
        this.print=print;
    }

    @Override
    public void run() {
        print.printChar();
    }
}
