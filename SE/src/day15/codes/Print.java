package day15.codes;

public class Print {
    private int count=1;

    public synchronized void printNum() {
        for (int i = 1; i <= 52; i++) {
            if (count % 3 == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.print(i+"\t");
            count++;
            this.notify();
        }
    }

    public synchronized void printChar() {
        for (char i = 'A'; i < 'z'; i++) {
            if (count % 3 != 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(i+"\t");
            count++;
            this.notify();
        }
    }
}
