package qian.ling.yi.jdk8;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Function
 *
 * @author liuguobin
 * @date 2018/3/20
 */

public class ClassTest {
    @Test
    public void testMethod() {
        Function<String, String> f = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s;
            }
        };

        System.out.println(f.apply("s"));


        Consumer consumer = new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.println(o);
            }
        };

        consumer.accept("S");


        Supplier supplier = new Supplier() {
            @Override
            public Object get() {
                return "S";
            }
        };
        System.out.println(supplier.get());


        Predicate predicate = new Predicate() {
            @Override
            public boolean test(Object o) {
                return "s".equals(o);
            }
        };
        System.out.println(predicate.test("s"));
    }
}
