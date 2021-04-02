package day11.homwork.test3;

public class V2Filter implements Filter {
    @Override
    public void filterUser(Users users) {
        users.setUserType("V2");
    }
}
