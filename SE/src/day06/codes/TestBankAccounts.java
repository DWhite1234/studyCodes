package day06.codes;
public class TestBankAccounts {
    public static void main(String[] args) {
        BankAccounts.rate = 0.035;
        BankAccounts bank1 = new BankAccounts();
        BankAccounts bank2 = new BankAccounts();
        bank1.account = "111111111111111111";
        bank1.money=55555.0;
        bank2.account = "222222222";
        bank2.money = 66655.5;
        bank1.show();
        bank2.show();
    }
}
