package day05.homework;

public class Test14 {
    public static void main(String[] args) {
        String[][] str = new String[][]{{"黑桃", "红桃", "梅花", "方块"}, {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}};
        for (int i = 0; i < str[0].length; i++) {
            for (int j = 0; j < str[1].length; j++) {
                System.out.print(str[0][i]+str[1][j]+"\t");
            }
            System.out.println();
        }
    }
}
