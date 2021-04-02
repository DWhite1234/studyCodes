package day15.codes;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class TestAPI {
    @Test
    public void test() {
        Date date =new Date();
        System.out.println("date = " + date);

        System.out.println("date.getTime() = " + date.getTime());

        Date date1 = new Date(1615980005974l);
        System.out.println(date1);

        System.out.println("date.toLocaleString() = " + date.toLocaleString());
    }

    @Test
    public void test01() {
        //按照指定日期格式格式化
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println("df.format(date) = " + df.format(date));
    }

    @Test
    public void test02() throws ParseException {
        //把指定日期解析为日期类型
        String str = "2017-12-25 12:55:55";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parse = df.parse(str);
    }

    @Test
    public void test03() {
        LocalDate date=LocalDate.now();
        date.isLeapYear();
        System.out.println(date);

        LocalTime time=LocalTime.now();
        System.out.println("time = " + time);

        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);
    }
}
