package day07.codes;

public class TestMax {

    public static void getMax(int... ints) {
        //找出最大的数
        int max=ints[0];
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] <= ints[i + 1]) {
                max = ints[i + 1];
            }
        }

        //最大数的所有公约数
        int []arr=new int[max];
        for (int i = 0; i <=max; i++) {
            if(max%(i+1)==0){
                arr[i]=i+1;
            }
        }
        boolean flag=true;
        int index=0;
        l:for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < ints.length; j++) {
                if(ints[j]%arr[i]!=0){
                    flag=false;
                    continue l;
                }
            }
        }
        if (flag){

        }
    }
}
