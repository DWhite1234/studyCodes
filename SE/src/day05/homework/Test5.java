package day05.homework;

public class Test5 {
    public static void main(String[] args) {
        int[] arr = new int[]{95, 92, 75, 56, 98, 71, 80, 58, 91, 91};
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        int count=0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>sum/arr.length){
                count++;
            }
        }
        System.out.println("平均分是:"+sum/arr.length);
        System.out.println("分数高于平均分的有:"+count+"个");
    }
}
