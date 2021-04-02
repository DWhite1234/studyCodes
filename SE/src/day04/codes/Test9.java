package day04.codes;

public class Test9 {
    public static void main(String[] args) {
        //数组的初始化方式
        //1.静态初始化
//        int [] arr1={1,3,4};//1
//        double[] arr2 = new double[]{3.0, 4.0, 0.0};//3.0

        //动态初始化
        int [] arr1=new int[3];//0
        byte[] arr3 = new byte[3];//0
        char [] arr4 =new char[3];//空格,"\u0000"
        short [] arr5=new short[5];//0
        long[] arr6 = new long[3];//0
        double[] arr2 = new double[3];//0.0
        float [] arr7 =new float[3];//0.0
        boolean [] arr8=new boolean[3];//false
        //引用数据类型
        String [] str=new String[3];//null
        System.out.println(arr1[0]+"\tint");
        System.out.println(arr2[0]+"\tdouble");
        System.out.println(arr3[0]+"\tbyte");
        System.out.println(arr4[0]+"\tchar");
        System.out.println(arr5[0]+"\tshort");
        System.out.println(arr6[0]+"\tlong");
        System.out.println(arr7[0]+"\tfloat");
        System.out.println(arr8[0]+"\tboolean");
        System.out.println(str[0]+"\t引用数据类型");
    }
}
