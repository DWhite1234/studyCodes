package day11.homwork.test2;

public class Player {
    static FightAble select(String string) {
        if(string=="法力角色"){
            return new Mage();
        }else if(string=="武力角色"){
            return new Soldier();
        }
        return null;
    }
}
