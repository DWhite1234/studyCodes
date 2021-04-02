package day22.notes.stream;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TestStream {
    /**
     * stream 流是对集合处理的一种补充,充分使用了8的新特性,Lambda表达式,极大的提高了效率,与简洁代码
     */
    @Test
    public void test01() {
        //获取流对象
        Stream<Integer> integerStream = Stream.of(1, 3, 45, 6);
        /* 最初版,Stream流处理集合
        integerStream.filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                if (integer < 2) {
                    return false;
                }
                return true;
            }
        }).forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
         */

        /* 流处理配合Lambda,极大的简化了代码,单还不是最终版本
        integerStream.filter((a)->{return a>2;}).forEach((a)-> System.out.println(a));

         */

        /*流处理,配合Lambda,配合方法引用,构造引用可以再次简化 Lambda
        integerStream.filter((a)->{return a>2;}).forEach(System.out::println);

         */
        System.out.println(integerStream);
    }
}
