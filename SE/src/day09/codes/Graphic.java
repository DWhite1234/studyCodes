package day09.codes;

public class Graphic {
    private String name;

    public Graphic() {
    }

    public Graphic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getArea() {
        return 0.0;
    }

    public double getPerimeter() {
        return 0.0;
    }

    public String getInfo() {
        return "图形名称:"+name+"图形面积:"+getArea()+"图形周长:"+getPerimeter();
    }
}
