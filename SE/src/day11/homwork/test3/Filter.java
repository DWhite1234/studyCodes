package day11.homwork.test3;

public interface Filter {
    void filterUser(Users users);
    default void show(){};
}
