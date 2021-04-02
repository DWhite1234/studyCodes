package day11.homwork.test3;

public class V1Filter implements Filter {
    @Override
    public void filterUser(Users users) {
        users.setUserType("V1");
    }
}
