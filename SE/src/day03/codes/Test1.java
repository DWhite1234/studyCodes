package day03.codes;

public class Test1 {
    public static void main(String[] args) {
        //四位数 1000-9999
        int count=0;
        for (int i=1000;i<=9999;i++){
            int a=i/1000;//千位
            int b=i%10;//个位
            int c=i/100%10;//百位
            int d=i%100/10;//十位
            if(b+c==a+d && b%2==0 && a%2!=0){
                System.out.print(i+" ");
                count++;
                if(count%5==0){
                    System.out.println();
                }
            }
        }
        System.out.println("个数为:"+count);
    }
}
