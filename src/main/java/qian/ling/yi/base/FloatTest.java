package qian.ling.yi.base;

import org.junit.Test;

/**
 * FloatTest
 *
 * @author liuguobin
 * @date 2018/5/5
 */

public class FloatTest {

    @Test
    public void testEqual() {
        Float fa = 1.2f;
        Float fb = 1.2f;
        System.out.println(fa.equals(fb));
        System.out.println(fa == fb);

        float fc = 1.2f;
        float fd = 1.2f;
        System.out.println(fc == fd);
    }
}
