package day04.codes;

public class Test2 {
    public static void main(String[] args) {
        for (int i = 1; i < 200; i++) {
            if (i % 3 == 0) {
                continue;
            }
            if (i % 10 == 3) {
                continue;
            }
            System.out.println(i);
        }
    }
}
