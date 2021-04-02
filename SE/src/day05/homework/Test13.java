package day05.homework;

public class Test13 {
    public static void main(String[] args) {
        int n=6;
        int [][] trangle=new int[n][];
        for (int i = 0; i <trangle.length; i++) {
            trangle[i]=new int[i+1];
            for (int j = 0; j <i+1; j++) {
                if(j==0||j==i){
                    trangle[i][j]=1;
                }
                if(i>1&&j>=1&&j<=i-1){
                    trangle[i][j]=trangle[i-1][j-1]+trangle[i-1][j];
                }
                System.out.print(trangle[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
