package day13.codes;

public class Test2 {
    public static void main(String[] args) {
        show();
    }

    private static void show() {
        int i=0;
        if (i == 0) {
            throw new NullPointerException("数字为空");
        }
    }
}
