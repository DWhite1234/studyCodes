package com.demo.utils;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class GetDateTest extends TestCase {


    @Test
    public void test() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,1);
        System.out.println("calendar = " + calendar.getTime());
        System.out.println("new Date() = " + new Date());
    }
}