package day08.codes;

public class TestPoint {
    public static void main(String[] args) {
        Point point = new Point();
        point.x=10.0;
        point.y = 5.2;

        System.out.println("方法调用前:x="+point.x+"\ty="+point.y);
        Point point1=point.changeNum(point);
        System.out.println("方法调用后:x="+point1.x+"\ty="+point1.y);
    }
}
