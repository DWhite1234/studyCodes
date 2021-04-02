package day09.codes;

public class ZuoBiao {
    private int x;
    private int y;

    public static double getJuLi(ZuoBiao z,ZuoBiao z1) {
        return Math.sqrt((z.x-z1.x)*(z.x-z1.x)+(z.y-z1.y)*(z.y-z1.y));
    }
    public ZuoBiao() {
    }

    public ZuoBiao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
