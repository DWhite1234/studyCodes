package day08.codes;

public class Point {
    double x;
    double y;

    public Point changeNum(Point p) {
        double temp=p.y;
        p.y = p.x;
        p.x = temp;
        System.out.println("方法调用中:x="+p.x+"\ty="+p.y);
        return p;

    }
}
