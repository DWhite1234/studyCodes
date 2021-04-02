package com.atguigu;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTest {
    @Test
    public void testHello() {
        TestHello testHello = new TestHello();
        assertEquals("Helloatguigu!", testHello.sayHello("atguigu"));
    }
}