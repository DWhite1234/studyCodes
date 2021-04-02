package day11.homwork.test2;

public interface FightAble {
    void specialFight();
    default void commonFight(){
        System.out.println("普通攻击");
    }
}
