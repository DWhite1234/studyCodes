package day11.homwork;

public interface Test {
    static void test1(){
        System.out.println("我是接口");
    };

    default void test2() {
        System.out.println("接口默认方法");
    }
}

class Father{
   static void tes1(){
        System.out.println("继承类");
    }

    void test2() {
    }
}

class Son extends Father implements Test {
    @Override
    public void test2() {
        System.out.println("子类重写test2");
    }

//    @Override
//    public void test2() {
//
//    }
}

class TT{
    public static void main(String[] args) {
        Son son = new Son();
//        Son.tes1();
        son.test2();
    }
}
