package day07.codes;

public class Test2 {
    public static void main(String[] args) {
        printPic(10, 10);
    }

    public static void printPic(int l, int w) {
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
