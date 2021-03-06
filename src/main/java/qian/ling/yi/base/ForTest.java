package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 测试for循环
 * Created by liuguobin on 2016/11/3.
 */
public class ForTest extends AbstractTest {
    int i = 0;
    int i1 = 1;
    int i2 = 2;
    private static final AtomicLong seedUniquifier
            = new AtomicLong(8682522807148012L);

    /**
     * 死循环
     */
    @Test
    public void test() {
        int i = 0;
        for (;;) {
            logger.info("{}", i++);
        }
    }

    /**
     * NullPointerException
     */
    @Test
    public void testNull() {
        List<String> list = null;
//        爆空指针
        list.forEach(str -> logger.info(str));
        for (int i = 0; i < list.size(); i ++) {
            logger.info(list.get(i));
        }
    }

    /**
     * hashMap 看到此种用法，新定义个变量
     */
    @Test
    public void ttt() {
        for (ForTest i = this, p;;) {

        }
    }
}


