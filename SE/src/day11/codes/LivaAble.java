package day11.codes;

public interface LivaAble {
    void  eat();

    void breath();

    default void sleep() {
        System.out.println("静止不动");
    };

    static void drink(){
        System.out.println("喝水");
    };
}
