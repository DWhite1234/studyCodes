package day12.codes.test1;

import java.util.Arrays;
import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        Employee[] employees = {
                new Employee(001, "张三", 2500.0),
                new Employee(003, "李四", 1500.0),
                new Employee(002, "王五", 5000.0),
                new Employee(005, "赵六", 500.0),
                new Employee(004, "孙琦", 9500.0),
        };
        //按照薪资比较
        Arrays.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.getSalary() - o2.getSalary() > 0) {
                    return 1;
                } else if (o1.getSalary() - o2.getSalary() < 0) {
                    return  -1;
                }else{
                    return 0;
                }
            }
        });

        System.out.println("Arrays.toString(employees) = " + Arrays.toString(employees));
    }
}
