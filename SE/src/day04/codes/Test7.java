package day04.codes;

public class Test7 {
    public static void main(String[] args) {
        l: for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                if (j == 6) {
                    break l;
                }
                System.out.println(j);
            }
            for (int k = 0; k <= 10; k++) {
                System.out.println(k);
            }
        }
    }
}
