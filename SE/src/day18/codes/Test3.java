package day18.codes;


public class Test3 {
    public static void main(String[] args) {
        Person<Animal> animalPerson = new Person<>("许仙",new Animal("精钢"));

        System.out.println("animalPerson = " + animalPerson);

    }
}
class Person<T>{
    String name;
    T company;

    public Person(String name, T company) {
        this.name = name;
        this.company = company;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", company=" + company +
                '}';
    }
}
class Animal{
    String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }
}
