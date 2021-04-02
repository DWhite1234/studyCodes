package day10.codes;

public class TestTraffic {
    public static void main(String[] args) {
        Traffic[] t = new Traffic[]{new Car(), new Bicycle()};
        for (Traffic traffic : t) {
            traffic.drive();
        }
    }
}
