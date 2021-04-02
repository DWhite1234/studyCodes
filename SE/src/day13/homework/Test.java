package day13.homework;

public class Test {
    public static void main(String[] args) {
        int test = test(3,5);
        System.out.println(test);
    }

    public static int test(int x, int y){
        int result = x;
        try{
            if(x<0 || y<0){
                return 0;
            }
            result = x + y;
            return result;
        }finally{
            result = x - y;
            return result;
        }
    }
}

class Test02 {
    public static void main(String[] args) {
        try{
            return;
        }finally{
            System.out.println("finally");
        }
    }
}
