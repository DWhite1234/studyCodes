package day11.codes;

public class Plant implements LivaAble {
    @Override
    public void eat() {
        System.out.println("吸收营养");
    }

    @Override
    public void breath() {
        System.out.println("吸入氧气呼出二氧化碳");
    }
}
