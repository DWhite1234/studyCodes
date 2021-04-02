package day05.codes;

public class Test6 {
    public static void main(String[] args) {
        int result =0;
        int num=0;
        l:for (int a = 0; a < 10; a++) {
            boolean flag=true;
            result = (int) (Math.random()*100);
            for (int i = 2; i < result; i++) {
                if (result % i == 0) {
                    continue l;
                }
            }
            if (flag) {
                num++;
                System.out.println(result);
            }
        }
    }
}
