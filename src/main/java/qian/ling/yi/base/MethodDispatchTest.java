package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

/**
 * 方法分派测试
 *
 * @author liuguobin
 * @date 2017/6/17
 */

public class MethodDispatchTest extends AbstractTest {

    class man{}
    class human extends man{}
    class women extends man{}

    void test(man man) {
        System.out.println(man);
    }
//    void test(human human) {
//        System.out.println(human + "    h");
//    }

        void test(human... human) {
        System.out.println(human + "    h");
    }
    void test(women women) {
        System.out.println(women + "    w");
    }
    @Test
    public void test() {
        human man = new human();
        test(man);

        man man1 = new human();
        test(man1);
    }
}
