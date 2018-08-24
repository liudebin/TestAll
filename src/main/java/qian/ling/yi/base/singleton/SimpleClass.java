package qian.ling.yi.base.singleton;

import qian.ling.yi.base.SuperClass;

/**
 * @author liuguobin
 * @date 2018/5/21
 */

public class SimpleClass extends SuperClass {
    static {
        System.out.println("SimpleClass static init");
    }

    public SimpleClass() {
        System.out.println("SimpleClass init");
//        i = 4;
    }
    public void testSimple() {
        System.out.println("simple " );
    }
}
