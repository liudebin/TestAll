package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

/**
 * BooleanTest
 *
 * @author liuguobin
 * @date 2018/6/8
 */

public class BooleanTest extends AbstractTest {

    @Test
    public void packages() {
        Boolean b = true;
        Boolean c = true;
        System.out.println(b == true);
        System.out.println(b == c);
    }

    // 短路或
    @Test
    public void testMethod() {
        int i = 1;
        System.out.println(i > 1||i++>1);
        System.out.println(i);
        System.out.println(i > 1||i++>1);
        System.out.println(i);

        System.out.println(i > 1|i++>1);
        System.out.println(i);
    }
}
