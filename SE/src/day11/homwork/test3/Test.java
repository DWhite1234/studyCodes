package day11.homwork.test3;

public class Test {

    public static void main(String[] args) {
        Recepionist r1 = new Recepionist(new V1Filter());
        Recepionist r2 = new Recepionist(new V2Filter());
        Recepionist r3 = new Recepionist(new AFilter());

        Recepionist[] arr = {r1, r2, r3};
        Users users=null;
        for (int i = 1; i <=15; i++) {
            users = new Users(i);
            if (i >= 1 && i <= 5) {
                r1.setUserLevel(users);
            } else if (i >= 6 && i <= 10) {
                r2.setUserLevel(users);
            }else{
                r3.setUserLevel(users);
            }
            System.out.println(users.getId()+"-"+users.getUserType());
        }
    }
}
