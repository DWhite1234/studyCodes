package day10.codes;

public class SalaryEmployee extends Employee {
    private double salary;
    private int workDays;
    private int qingJia;

    @Override
    public double earn() {
        return salary - salary / workDays * qingJia;
    }

    public SalaryEmployee() {
    }

    public SalaryEmployee(String name, double salary, int workDays, int qingJia) {
        super(name);
        this.salary = salary;
        this.workDays = workDays;
        this.qingJia = qingJia;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getWorkDays() {
        return workDays;
    }

    public void setWorkDays(int workDays) {
        this.workDays = workDays;
    }

    public int getQingJia() {
        return qingJia;
    }

    public void setQingJia(int qingJia) {
        this.qingJia = qingJia;
    }
}
