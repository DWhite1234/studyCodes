package day15.codes;

public class Test {
    public static void main(String[] args) {
        Print print=new Print();

        PrintChar printChar = new PrintChar("字母线程",print);
        PrintNum printNum = new PrintNum("数字线程",print);

        printNum.start();
        printChar.start();
    }
}
