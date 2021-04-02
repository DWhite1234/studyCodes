package day17.codes;

import org.junit.Test;

public class TestBuilder {

    @Test
    public void test01() {
        StringBuilder stringBuilder = new StringBuilder("床前明夜光");
        //从指定下标增加
        stringBuilder.insert(stringBuilder.length(), "意思地上霜");
        //追加
        stringBuilder.append("巨头王明月");
        //删除指定下标范围
        stringBuilder.delete(5, 10);
        //修改指定下标的值
        stringBuilder.setCharAt(0, '小');
        //字符串长度
        stringBuilder.length();
        //修改字符串长度
        stringBuilder.setLength(30);
        System.out.println("stringBuilder = " + stringBuilder);
    }

    @Test
    public void test02() {
        /*
        StringBuffer和StringBuilder的区别:
            1.StringBuffer
                线程安全的,效率低
            2.StringBuilder
                线程不安全的,效率高
         */

        //效率测试
        //String
        //StringBuffer   StringBuilder 用时:36
        //StringBuilder  StringBuffer 用时:48

        long start = System.currentTimeMillis();
        String str="";
        for (int i = 0; i < 10_0000; i++) {
            str += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("String 用时:"+(end-start));
    }

    @Test
    public void test03() {
        long start = System.currentTimeMillis();
        StringBuilder str=new StringBuilder("");
        for (int i = 0; i < 100_0000; i++) {
            str.append(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("StringBuilder 用时:"+(end-start));
    }

    @Test
    public void test04() {
        long start = System.currentTimeMillis();
        StringBuffer str=new StringBuffer("");
        for (int i = 0; i < 100_0000; i++) {
            str.append(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("StringBuffer 用时:"+(end-start));
    }
}
