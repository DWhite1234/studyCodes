package day22.notes.optional;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Supplier;

public class TestOptional {

    /**
     * optional类的作用:
     *  用于解决显示的判断空指针的问题
     */
    @Test
    public void test01() throws Throwable {
        String str = null;
        //创建optional对象,与Optional.of()一样
        Optional<String> optional = Optional.ofNullable(str);
        //get()获取容器中的对象,如果是null则会抛异常No value present,否则返回对象
//        String s = optional.get();

        //如果该值为null为输出,参数中的字符串,否则原样输出
        String s1 = optional.orElse("该值为null");

        //该方法与get作用一样,如果是null则会抛异常No value present,否则返回对象
        String orElseGet = optional.orElseGet(new Supplier<String>() {
            @Override
            public String get() {
                return "该值也为null";
            }
        });

        //如果为空会,抛出指定异常,甩锅专用
        String elseThrow = optional.orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new NullPointerException("该值耶耶耶为空");
            }
        });
        System.out.println("elseThrow = " + elseThrow);
        System.out.println("orElseGet = " + orElseGet);
        System.out.println("s1 = " + s1);
//        System.out.println("s = " + s);

    }
}
