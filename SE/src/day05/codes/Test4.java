package day05.codes;

public class Test4 {
    public static void main(String[] args) {
        int[] arr = new int[]{10, 50, 20, 70, 9};
        int max=arr[0];
        int min=arr[0];
        int maxIndex=0;
        int minIndex=0;
        for (int i = 0; i < arr.length; i++) {
            if(max<=arr[i]){
                max = arr[i];
                maxIndex=i;
            }
            if (min >= arr[i]) {
                min=arr[i];
                minIndex=i;
            }
        }

//        for (int i=0;i<arr.length;i++) {
//            if(max==arr[i]){
//                maxIndex=i;
//            }
//            if (min == arr[i]) {
//                minIndex=i;
//            }
//        }
        System.out.println("最大值为:"+max+",下标为:"+maxIndex);
        System.out.println("最小值为:"+min+",下标为:"+minIndex);
    }
}
