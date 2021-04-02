package day22.codes;

public class TestLambda {

    public static void main(String[] args) {
        Show show = s -> System.out.println(s);
        show.tt("哈哈");
    }
}

interface Show{
    void tt(String s);
}
