package qian.ling.yi.ext.vavr;

import io.vavr.collection.List;
import org.junit.Test;

public class ListTest {

    /**
     * 新的不可变list
     */
    @Test
    public void testBase() {
        List<Integer> of = List.of(1, 2, 3);
        of = of.append(4);
        List<Integer> prepend = of.tail().prepend(0);
        System.out.println(of);

        System.out.println(prepend);
        System.out.println(of);
    }
}
