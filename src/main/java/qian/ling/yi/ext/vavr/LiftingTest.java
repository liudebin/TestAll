package qian.ling.yi.ext.vavr;

import io.vavr.Function2;
import io.vavr.control.Option;
import org.junit.Test;

public class LiftingTest {

    @Test
    public void test() {
        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);

// = None
        Option<Integer> i1 = safeDivide.apply(1, 0); //(1)
        System.out.println(i1);
        System.out.println(i1.isDefined());

// = Some(2)
        Option<Integer> i2 = safeDivide.apply(4, 2); //(2)
        System.out.println(i2);
        System.out.println(i2.get());
        System.out.println(i2.isDefined());
    }
}
