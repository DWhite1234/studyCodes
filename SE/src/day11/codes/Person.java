package day11.codes;

public class Person {
    String name;
    int age;
    double score;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }

    public Person() {
    }

    public Person(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }
}
