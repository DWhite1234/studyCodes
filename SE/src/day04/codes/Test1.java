package day04.codes;

public class Test1 {
    public static void main(String[] args) {
        for (int i=1;i<=200;i++){
            if(i%3==0){
                System.out.print("foo ");
            }
            if(i%5==0){
                System.out.print("baz ");
            }
            if (i%7==0){
                System.out.print("biz ");
            }
            System.out.println();
        }
    }
}
