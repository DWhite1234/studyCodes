package day07.codes;

public class TestJuXing {
    public static void main(String[] args) {
        JuXing jx = new JuXing();

        jx.length=19;
        jx.width=10;

        int area = jx.calArea(jx.length, jx.width);
        System.out.println("area = " + area);

        int calTotalLength = jx.calTotalLength(jx.length, jx.width);
        System.out.println("calTotalLength = " + calTotalLength);

        String s = jx.showObject(jx.length, jx.width);
        System.out.println("s = " + s);
    }
}
