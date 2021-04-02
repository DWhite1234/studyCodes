package day08.codes;

public class Step {
    public static void main(String[] args) {
        System.out.println("getSteps = " + getSteps(3));
    }

    public static int getSteps(int step) {
        if (step == 1) {
            return 1;
        }
        if (step == 2) {
            return 2;
        }
        return getSteps(step - 1) + getSteps(step - 2);
    }
}
