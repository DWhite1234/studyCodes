package day17.codes.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class TestCollection {
    Collection cs = new ArrayList();
    Collection c2=new ArrayList();

    @Before
    public void test04() {
        cs.add("李白");
        cs.add(10);
        cs.add(10);
        cs.add(new Person("张三"));
    }

    @Test
    public void test07(){
        System.out.println(cs);
    }
    //其他方法
    @Test
    public void test06() {
        //判断是否为空
        boolean empty = cs.isEmpty();

        //交集
        boolean b = cs.retainAll(c2);

        //是否包含某个元素
        boolean contains = cs.contains("李白");

        //是否包含某个集合
        boolean containsAll = cs.containsAll(c2);

        //集合长度
        int size = cs.size();
    }

    //集合转数组
    @Test
    public void test05() {
        Object[] objects = cs.toArray();
    }

    //遍历
    @Test
    public void test03() {
        //增强for
        for (Object c : cs) {
            System.out.println(c);
        }
        //迭代器
        Iterator iterator = cs.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(next);
        }
    }
    //删除
    @Test
    public void test02() {
        test01();
        //删除单个属性
        cs.remove("李白");
        System.out.println(cs);
        //删除一个集合
        cs.remove(c2);
        //删除与集合相同的内容
        boolean b = cs.removeAll(c2);
        System.out.println(cs);
        //清空集合
        cs.clear();
        System.out.println(cs);
    }

    //添加
    @Test
    public void test01() {
        //添加属性
        cs.add("李白");
        cs.add(10);
        cs.add(new Person("张三"));
        System.out.println(cs);
        c2.add("赌徒");
        c2.add("杜甫");

        //把一个集合的所有数据拿出来放进去
        cs.addAll(c2);
        System.out.println(cs);
        //把一个集合当做整体添加
        cs.add(c2);

        System.out.println(cs);

    }
}

class Person{
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
