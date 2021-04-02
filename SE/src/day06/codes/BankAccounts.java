package day06.codes;

public class BankAccounts {
    static double rate;
    String account;
    double money;

    public void show() {
        System.out.println("账户是:" + account);
        System.out.println("余额是:" + money);
        System.out.println("利率是:" + rate);
    }
}
