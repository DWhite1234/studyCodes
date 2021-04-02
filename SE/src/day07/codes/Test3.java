package day07.codes;

public class Test3 {
    public static void main(String[] args) {
        System.out.println("isZhiShu(97) = " + isZhiShu(97));
    }

    public static String isZhiShu(int a){
        boolean flag =true;
        for (int i = 2; i < a-1/2; i++) {
            if (a % i == 0) {
                flag=false;
                break;
            }
        }
        String s = flag ? "是质数" : "不是质数";
        return s;
    }
}
