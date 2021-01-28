package qian.ling.yi.ext.vavr;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TupleTest {
    @Test
    public void testTuple() {
        Tuple2<String, Integer> java = Tuple.of("Java", 8);
        String apply = java.apply((a, b) -> a + b);
        System.out.println(apply);
    }
}
