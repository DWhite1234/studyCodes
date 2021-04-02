package day11.codes;

import java.util.Arrays;

public class TestPerson {
    public static void main(String[] args) {
        Person[] person = new Person[]{new Person("张三", 20, 18),
                new Person("李四", 18, 20),
                new Person("王五", 23, 19)};

        Sort sort = new Sort();
        for (int i = 0; i < person.length-1; i++) {
            for (int j = 0; j < person.length - i - 1; j++) {
                if (sort.compare(person[j], person[j + 1]) > 0) {
                    Person temp = person[j + 1];
                    person[j + 1] = person[j];
                    person[j] = temp;
                }
            }
        }
        System.out.println("Arrays.toString(person) = " + Arrays.toString(person));
    }
}
