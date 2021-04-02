package day03.codes;

public class TestSum {
    public static void main(String[] args) {
        int sum=0;
        int count=0;
        for (int i=1;i<=100;i+=2){
            sum+=i;
            count++;
        }
        System.out.println("奇数和为:"+sum);
        System.out.println("奇数个数为:"+count);
    }
}
