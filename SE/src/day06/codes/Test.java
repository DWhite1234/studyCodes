package day06.codes;

import day06.codes.Cat;

public class Test {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.color = "yellow";
        System.out.println("cat.color = " + cat.color);
        cat.sleep();
    }
}
