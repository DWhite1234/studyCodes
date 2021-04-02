package day06.codes;

import java.awt.geom.Area;

public class Circle {
    double r;

    public void calAreas(){
        System.out.println("半径为:" + r);
        System.out.println("面积为:"+Math.PI*r*r);
    }
}
