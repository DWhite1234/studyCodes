package day11.homwork.test3;

public class Users {
    private String userType;
    private int id;

    public String getUserType() {
        return userType;
    }

    public Users(int id) {
        this.id = id;

    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users() {
    }

    public Users(String userType, int id) {
        this.userType = userType;
        this.id = id;
    }
}
