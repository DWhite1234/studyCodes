package day08.codes;

public class GraphicTools {
    double a;
    double b;
    double c;

    public double getArea() {
        double p = (a + b + c) / 2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-b));
    }

    public double getArea(double bottom, double height) {
        return bottom * height / 2;
    }
}
