package day13.codes;

public class Test3 extends NullPointerException {
    public Test3(String s) {
        super(s);
    }
}

class dd {
    public static void main(String[] args) {
        throw new Test3("自定义异常类");
    }
}
