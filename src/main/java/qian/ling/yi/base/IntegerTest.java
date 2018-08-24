package qian.ling.yi.base;

import org.junit.Test;

/**
 * IntegerTest
 *
 * @author liuguobin
 * @date 2018/5/5
 */

public class IntegerTest {

    @Test
    public void testEqual() {
        Integer i1 = 1;
        Integer i2 = 1;
        System.out.println(i1 == i2);

        Integer i3 = 1000;
        Integer i4 = 1000;
        System.out.println(i3 == i4);
        System.out.println(i3.equals(i4));

        Integer i7 = 127;
        Integer i8 = 127;
        System.out.println(i7 == i8);
        System.out.println(i7.equals(i8));

        Integer i9 = 128;
        Integer i10 = 128;
        System.out.println(i9 == i10);
        System.out.println(i9.equals(i10));



        int i5 = 1000;
        int i6 = 1000;
        System.out.println(i5 == i6);

    }
}
