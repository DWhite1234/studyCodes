package day10.codes;

public class HourEmployee extends Employee {
    private int hours;
    private double hourSalary;

    @Override
    public double earn() {
        return hours * hourSalary;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public double getHourSalary() {
        return hourSalary;
    }

    public void setHourSalary(double hourSalary) {
        this.hourSalary = hourSalary;
    }

    public HourEmployee() {
    }

    public HourEmployee(String name, int hours, double hourSalary) {
        super(name);
        this.hours = hours;
        this.hourSalary = hourSalary;
    }
}
