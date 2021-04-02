package day11.codes;

public class TestLiveAble {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.eat();
        animal.breath();
        animal.sleep();
        System.out.println("--------------------");
        Plant plant = new Plant();
        plant.eat();
        plant.breath();
        plant.sleep();
        System.out.println("--------------------");
        LivaAble.drink();
    }
}
