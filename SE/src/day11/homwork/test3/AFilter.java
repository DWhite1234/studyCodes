package day11.homwork.test3;

public class AFilter implements Filter {
    @Override
    public void filterUser(Users users) {
        users.setUserType("A");
    }
}
