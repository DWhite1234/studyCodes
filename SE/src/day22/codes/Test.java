package day22.codes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
    /**
     * 反射操作静态属性
     * @throws Exception
     */
    @org.junit.Test
    public void test01() throws Exception {
        //获取class对象
        Class<Person> aClass = Person.class;
        //获取指定构造器
        Constructor<Person> constructor = aClass.getDeclaredConstructor(int.class);
        //设置私有可见
        constructor.setAccessible(true);
        //反射创建对象
        Person person = constructor.newInstance(10);
        System.out.println("person = " + person);
    }

    /**
     * 反射操作方法
     */
    @org.junit.Test
    public void test02() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //获取运行时类型
        Class<Person> personClass = Person.class;
        //反射创建对象
        Person person = personClass.newInstance();
        //运行时类型调用私有方法
        Method show = personClass.getDeclaredMethod("showPrivate");
        Method showStatic = personClass.getDeclaredMethod("showStatic");
        //设置私有可见
        show.setAccessible(true);
        //私有方法调用,没有参数写null
        show.invoke(person, null);
        //静态方法调用,不需要对象,写null
        showStatic.invoke(null, null);
        System.out.println(person);
    }
}

class Person{
    String name;
    private int age;

    public void show() {
        System.out.println("普通方法");
    }

    private void showPrivate() {
        System.out.println("私有方法");
    }

    public static void showStatic() {
        System.out.println("静态方法");
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Person() {
    }

    private Person(int age) {
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
