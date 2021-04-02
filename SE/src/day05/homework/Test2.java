package day05.homework;

public class Test2 {
    public static void main(String[] args) {
        String [] arr1=new String[]{"黑桃", "红桃", "梅花", "方块"};
        String[] arr2 = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9","10", "J", "Q", "K"};
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                System.out.print(arr1[i]+arr2[j]+"\t");
            }
            System.out.println();
        }
    }
}
