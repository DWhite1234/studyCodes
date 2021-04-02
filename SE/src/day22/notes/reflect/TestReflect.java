package day22.notes.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestReflect {

    /**
     * 反射创建对象
     */
    @Test
    public void test01() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //获取class对象
        Class aClass = Person.class;
        //只能获取所有的public 构造函数
        Constructor[] constructors = aClass.getConstructors();
        //获取所有的构造函数
        Constructor<Person>[] declaredConstructors = aClass.getDeclaredConstructors();
        //获取指定的构造函数
        Constructor<Person> constructor = aClass.getDeclaredConstructor(null);
        //如果该构造器是私有的,需要设置setAccessible(true)才能赋值
        //constructor.setAccessible(true);
        //调用构造函数,创建对象
        Person o = constructor.newInstance();
        System.out.println(o);
    }

    /**
     * 反射调用方法
     */
    @Test
    public void test02() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<Person> aClass = Person.class;
        //获取所有public方法
        Method[] methods = aClass.getMethods();
        //获取所有方法
        Method[] declaredMethods = aClass.getDeclaredMethods();
        //获取指定方法,name=>方法名,后面跟方法的参数类型
        Method method = aClass.getDeclaredMethod("show",String.class);
        //设置方法私有可见
        method.setAccessible(true);
        //创建执行对象
        Person P = aClass.newInstance();
        //invoke需要一个执行对象,如果有返回值,invoke就是返回值
        Object invoke = method.invoke(P, "你好");
    }

    /**
     * 反射调用属性
     */
    @Test
    public void test03() throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        //获取class对象
        Class<Person> personClass = Person.class;
        //获取所有public属性
        Field[] declaredFields = personClass.getFields();
        //获取所有属性
        Field[] declaredFields1 = personClass.getDeclaredFields();
        //获取指定属性
        Field name = personClass.getDeclaredField("name");
        //设置私有属性可见
        name.setAccessible(true);
        //获取操作对象
        Person person = personClass.newInstance();
        //设置属性值,需要一个操作对象
        name.set(person, "张三");
    }
}
class Person{
    public String name;
    protected int age;
    private char sex;

    public void show(String string) {
        System.out.println(string);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Person() {
    }

    private Person(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}
