package day05.homework;

import java.util.Arrays;
import java.util.Scanner;

public class Test15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("全班共有几个组");
        int groupNum = scanner.nextInt();
        int[][] score = new int[groupNum][];
        //录入全班成绩
        for (int i = 0; i < score.length; i++) {
            System.out.println("第"+(i+1)+"组,有几个人");
            int num = scanner.nextInt();
            score[i]=new int[num];
            for (int j = 0; j < score[i].length; j++) {
                System.out.println("请输入,第" + (i + 1) + "组的,第" + (j + 1) + "个人的成绩");
                score[i][j] = scanner.nextInt();
            }
        }
        for (int[] ii : score) {
            for (int i : ii) {
                System.out.print(i+"\t");
            }
            System.out.println();
        }
        //统计,每组
        int [] allMax=new int[groupNum];
        int [] allMin=new int[groupNum];
        double [] allAvg=new double[groupNum];
        for (int i=0;i<score.length;i++) {
            //假设最高分为每组第一个
            int maxScore=score[i][0];
            //最低分为每组第二个
            int minScore = score[i][1];
            int totalGroup=0;
            for (int j = 0; j < score[i].length; j++) {
                //统计每组总分
                totalGroup+=score[i][j];
                //统计每组的最高分
                if (maxScore <= score[i][j]) {
                    int temp = score[i][j];
                    maxScore=score[i][j];
                    score[i][j] = temp;
                }
                //统计每组最低分
                if (minScore >= score[i][j]) {
                    int temp = score[i][j];
                    minScore=score[i][j];
                    score[i][j] = temp;
                }
            }
            allMax[i]=maxScore;
            allMin[i]=minScore;
            allAvg[i]=totalGroup*1.0/score[i].length;
            System.out.println("第"+(i+1)+"组,最高分为:"+maxScore);
            System.out.println("第"+(i+1)+"组,最低分为:"+minScore);
            System.out.println("第"+(i+1)+"组,平均分为:"+ allAvg[i]);
        }
        //统计全班
        int totalAll=0;
        //假设全班最高分为
        int totalMax=allMax[0];
        int totalMin = allMin[1];
        for (int j = 0; j < allMax.length; j++) {
            //统计全班的最高分
            if (totalMax <= allMax[j]) {
                int temp = allMax[j];
                totalMax = allMax[j];
                allMax[j] = temp;
            }
        }
        System.out.println("全班最高分为:"+totalMax);
        for (int j = 0; j < allMax.length; j++) {
            //统计全班的最低分
            if (totalMin >= allMax[j]) {
                int temp = allMax[j];
                totalMin = allMax[j];
                allMax[j] = temp;
            }
        }
        System.out.println("全班最低分为:"+totalMin);
        double totalAvg=0;
        for (int j = 0; j < allAvg.length; j++) {
            totalAvg += allMax[j];
        }
        System.out.println("全班平均分为:"+totalAvg/allAvg.length);
    }
}
