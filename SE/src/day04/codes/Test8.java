package day04.codes;

public class Test8 {
    public static void main(String[] args) {

        l:for (int a = 2; a <= 200; a++) {
            int count=0;
            for (int i = 1; i <= a; i++) {
                if (a % i == 0) {
                    count++;
                }
                if (count > 2) {
                    continue l;
                }
            }
            if (count == 2) {
                System.out.println(a);
            }
        }

    }
}
