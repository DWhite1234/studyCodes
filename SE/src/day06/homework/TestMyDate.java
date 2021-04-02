package day06.homework;

public class TestMyDate {
    public static void main(String[] args) {
        MyDate birthday = new MyDate();
        birthday.year=2020;
        birthday.month=11;
        birthday.day=22;

        MyDate arrival = new MyDate();
        arrival.year = 2021;
        arrival.month=02;
        arrival.day = 25;

        MyDate graducation = new MyDate();
        graducation.year = 2021;
        graducation.month = 9;
        graducation.day = 1;

        System.out.println("出生日期:" + birthday.year + "/" + birthday.month + "/" + birthday.day);
        System.out.println("来硅谷的日期:" + arrival.year + "/" + arrival.month + "/" + arrival.day);
        System.out.println("毕业日期:" + graducation.year + "/" + graducation.month + "/" + graducation.day);
    }
}
