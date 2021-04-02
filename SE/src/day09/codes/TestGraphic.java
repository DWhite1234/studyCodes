package day09.codes;

public class TestGraphic {
    public static void main(String[] args) {
        Circle circle = new Circle("圆",5.5);
        System.out.println("circle.getArea() = " + circle.getArea());
        System.out.println("circle.getPerimeter() = " + circle.getPerimeter());
        System.out.println("circle.getInfo() = " + circle.getInfo());

        Rectange rectange = new Rectange("矩形", 10, 5);
        System.out.println("rectange.getArea() = " + rectange.getArea());
        System.out.println("rectange.getPerimeter() = " + rectange.getPerimeter());
        System.out.println("rectange.getInfo() = " + rectange.getInfo());
    }
}
