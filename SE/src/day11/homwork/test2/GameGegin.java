package day11.homwork.test2;

public class GameGegin {
    public static void main(String[] args) {
        FightAble select = Player.select("法力角色");
        FightAble selected = Player.select("武力角色");
        select.specialFight();
        select.commonFight();

        selected.specialFight();
        selected.commonFight();
    }
}
