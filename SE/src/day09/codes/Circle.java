package day09.codes;

public class Circle extends Graphic {
    private double radius;

    public Circle() {
    }

    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI*radius*radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public String getInfo() {
        return super.getInfo()+"半径:"+radius;
    }

}
