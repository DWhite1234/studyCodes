package day10.codes;

public class Test {

	public static void main(String[] args) {
		Father f = new Son();
		f.test();//只看编译时类型
        f.method();
	}
}
class Father{
	public static void test(){
		System.out.println("Father.test");
	}
    public void method(){
        System.out.println("Father.method");
        fun();//看运行时类型
        other();//看编译时类型
    }
    public void fun(){
        System.out.println("Father.fun");
    }
    private void other(){
        System.out.println("Father.other");
    }
}
class Son extends Father{
	public static void test(){
		System.out.println("son");
	}
    public void fun(){
        System.out.println("Son.fun");
    }
    private void other(){
        System.out.println("Son.other");
    }
}