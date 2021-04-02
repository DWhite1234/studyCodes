package day04.homework;

public class Test1 {
    public static void main(String[] args) {
        int i =0;
        int i2=10;
        while (i < 5) {
            i++;
            i2++;
            if (i >=2 && i2 < 15) {
                System.out.print("i:"+i+"\t");
                System.out.println("i2:"+i2);
            }
        }
        System.out.println("-------------------------");
        while (i2 < 20) {
            i++;
            i2++;
            if (i > 8 || i2 <= 18) {
                System.out.print("i:"+i+"\t");
                System.out.println("i2:"+i2);
            }
        }
    }
}
