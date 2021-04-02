package day08.codes;

public class TestGraphicTools {
    public static void main(String[] args) {
        GraphicTools tools = new GraphicTools();
        tools.a=10.0;
        tools.b = 3.3;
        tools.c = 9.9;

        double area = tools.getArea();
        System.out.println("area = " + area);

        double area1 = tools.getArea(tools.a, tools.b);
        System.out.println("area1 = " + area1);
    }
}
