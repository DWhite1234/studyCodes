package day06.homework;

public class TestCitizen {
    public static void main(String[] args) {
        Citizen citizen = new Citizen();
        citizen.birthday=new MyDate();
        citizen.name = "小命";
        citizen.birthday.year = 2020;
        citizen.birthday.month = 10;
        citizen.birthday.day = 22;
        citizen.id = "54454566444654";
        System.out.println("citizen.name = " + citizen.name);
        System.out.println("citizen.birthday = " + citizen.birthday.year + "/" + citizen.birthday.month + "/" + citizen.birthday.day);
        System.out.println("citizen.id = " + citizen.id);
    }
}
