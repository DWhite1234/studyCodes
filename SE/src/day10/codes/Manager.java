package day10.codes;

public class Manager extends SalaryEmployee {
    private double rate;

    @Override
    public double earn() {
        return super.earn() * (1 + rate);
    }

    public Manager(String name, double salary, int workDays, int qingJia, double rate) {
        super(name, salary, workDays, qingJia);
        this.rate = rate;
    }

    public Manager() {
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
