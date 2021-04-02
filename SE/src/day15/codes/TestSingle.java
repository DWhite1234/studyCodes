package day15.codes;

public class TestSingle {
    public static void main(String[] args) {
        Single s1 = Single.getInstance();
        Single s2 = Single.getInstance();
        System.out.println(s1==s2);

        HungrySingle h1 = HungrySingle.getInstance();
        HungrySingle h2 = HungrySingle.getInstance();
        System.out.println(h1==h2);
    }
}
