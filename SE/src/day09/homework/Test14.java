package day09.homework;

public class Test14 {
    public static void main(String[] args) {
        Father f = new Father();
        Son s = new Son();
        System.out.println(f.getInfo());//atguigu
        System.out.println(s.getInfo());//atguigu
        s.test();//atguigu atguigu
        System.out.println("-----------------");
        s.setInfo("大硅谷");
        System.out.println(f.getInfo());//atguigu
        System.out.println(s.getInfo());//大硅谷
        s.test();//atguigu  大硅谷
        Son.show();
    }
}
class Father{
    private String info = "atguigu";
    public void setInfo(String info){
        this.info = info;
    }
    public String getInfo(){
        return info;
    }

    public static void show() {
        System.out.println("我是父类静态方法");
    }
}
class Son extends Father{
    private String info = "尚硅谷";
    public void setInfo(String info){
        this.info = info;
    }
    public String getInfo(){
        return info;
    }
    public void test(){
        System.out.println(this.getInfo());
        System.out.println(super.getInfo());
    }
}