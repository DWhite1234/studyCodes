package day05.homework;

import java.util.Arrays;

public class Test7 {
    public static void main(String[] args) {
        int []arr1=new int[]{1,3,4,5,6,7};
//        int []arr2=new int[]{1,5,3,5,7,8};
        int []arr2=new int[]{1,3,4,5,6,7};
        boolean flag=true;
        if(arr1.length==arr2.length){
            for (int i=0;i<arr1.length;i++) {
                if (arr1[i] != arr2[i]) {
                    flag=false;
                    break;
                }
            }
        }else{
            System.out.println("不一致");
        }
        System.out.println(Arrays.toString(arr1)+"\t"+Arrays.toString(arr2)+"是否一直"+flag);
    }
}
