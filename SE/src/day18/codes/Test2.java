package day18.codes;

public class Test2 {
    public static void main(String[] args) {
        Coordinate<String> objectCoordinate = new Coordinate<>("10","20");

        Coordinate<Double> doubleCoordinate = new Coordinate<>(new Double(30), new Double(40));

        System.out.println("objectCoordinate = " + objectCoordinate);
        System.out.println("doubleCoordinate = " + doubleCoordinate);
    }
}
class Coordinate<T>{
    T x;
    T y;

    public Coordinate(T x, T y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}