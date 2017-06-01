package qian.ling.yi.jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by liuguobin on 2017/2/9.
 */
public class InstantTest {
    @Test
    public void test() {
//        Instant instant = new Instant();
    }

    public void function() {
        Supplier<List<String>> supplier = ArrayList::new;
        Function<Integer, List> f = ArrayList::new;
        List<String>  aa = f.apply(8);

        aa.sort(Comparator.comparing(String::length));

        aa.sort(Comparator
                .comparing(String::length)
                .reversed()
                .thenComparing(String::hashCode));
    }

}
