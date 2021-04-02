package day14.codes;

public class TestPrint {
    public static void main(String[] args) {
        Print printNum = new Print("打印数字线程");
        Print printChar = new Print("打印字母线程");

        printChar.start();
        printNum.start();
    }
}
