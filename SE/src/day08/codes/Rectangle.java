package day08.codes;

public class Rectangle {
    private static int sides = 4;
    private double length;
    private double width;

    public static void setSides(int sides) {
        Rectangle.sides = sides;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public static int getSides() {
        return sides;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }
}
