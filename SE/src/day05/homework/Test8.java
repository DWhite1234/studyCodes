package day05.homework;

public class Test8 {
    public static void main(String[] args) {
        char[] answer = new char[]{'A','D', 'B', 'C', 'D'};
        char[] xiaoshang = new char[]{'D', 'C', 'B', 'A', 'D'};
        int score=0;
        for (int i = 0; i < answer.length; i++) {
            if (answer[i] == xiaoshang[i]) {
                score+=2;
            }
        }
        System.out.println("xiaoshang得分"+score);
    }
}
