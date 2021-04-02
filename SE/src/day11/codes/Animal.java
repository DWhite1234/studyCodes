package day11.codes;

public class Animal implements LivaAble{
    @Override
    public void eat() {
        System.out.println("吃东西");
    }

    @Override
    public void breath() {
        System.out.println("吸入氧气呼出二氧化碳");
    }

    @Override
    public void sleep() {
        System.out.println("闭眼睡觉");
    }
}
