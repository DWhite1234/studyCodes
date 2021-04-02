package day21.notes;

import org.junit.Test;

import java.io.*;

public class ObjetTest {
    /**
     * 对象流读取的时候,需要对象被序列化
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void test01() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:/obj.txt"));
        System.out.println("ois.readObject() = " + ois.readObject());
    }


    /**
     * 对象流写出时,需要该对象被序列化
     *
     * @throws IOException
     */
    @Test
    public void test02() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:/obj.txt"));
        oos.writeObject(new Person("张三", 10));
    }

}

class Person implements Serializable {
    /**
     * 如何防止某个属性被序列化
     * transient
     * static
     */
//    transient String name;
//    static  int age;
    String name;
    int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
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

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
