package day11.codes;

import org.omg.CORBA.ORB;

import java.util.Arrays;

public class TestGender {
    public static void main(String[] args) {
        Gender gender=Gender.MAN;
        Gender gender2=Gender.WOMAN;

        EnumGender man = EnumGender.MAN;
        EnumGender woman = EnumGender.WOMAN;
        EnumGender gender1 = EnumGender.valueOf("WOMAN");
        EnumGender[] values = EnumGender.values();
        int ordinal = gender1.ordinal();
        System.out.println("ordinal = " + ordinal);
        System.out.println("gender1 = " + gender1);
        System.out.println("values = " + Arrays.toString(values));
    }
}
