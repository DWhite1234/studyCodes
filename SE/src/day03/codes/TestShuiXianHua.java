package day03.codes;

public class TestShuiXianHua {
    public static void main(String[] args) {
        //求所有三位水仙花数 153
        for (int i=100;i<=999;i++){
            int a=i%10;//个位
            int b=i/10%10;//十位
            int c=i/100;//百位
//            System.out.println("a="+a+",b="+b+",c="+c);
            if (a * a * a + b * b * b + c * c * c == i) {
                System.out.println(i);
            }
        }

    }
}
