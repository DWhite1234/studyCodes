package day22.notes.Lambda;

import org.junit.Test;

import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TestLambda {

    /**
     * Lambda测试
     */
    @Test
    public void test01(){
        /*最初版,匿名内部类
        Supplier supplier = new Supplier<Person>() {
            @Override
            public Person get() {
                return new Person("张三");
            }
        };
        */
        /* 进化版 Lambda表达式版
        Supplier supplier = () -> new Person("李四");

         */
        /* 方法引用与构造引用简化Lambda表达式
        Supplier supplier = Person::new;

         */
        Supplier supplier = Person::new;
        Object o = supplier.get();
        System.out.println("o = " + o);
    }
}
class Person{
    String name;

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    public Person(String name) {
        this.name = name;
    }
}
