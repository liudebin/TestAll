package qian.ling.yi.thread.atomic;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liuguobin on 2016/11/3.
 */
public class AtomTest extends AbstractTest {

    AtomicInteger atomicInteger = new AtomicInteger(1000);

    /**
     * 原子类会先比较是否等于current，等于则设置为next
     */
    @Test
    public void test() {
        int current = atomicInteger.get();
        int next = current * 1817;
        if (atomicInteger.compareAndSet(current, next))
            logger.info("{}", next);
    }

    /**
     * 设定值，只有设定成功才会返回值
     * @param atomInt
     * @param value
     * @return
     */
    public int setValue(AtomicInteger atomInt, int value) {
        for (;;) {
            int current = atomInt.get();
            int next = current * 181;
            if (atomInt.compareAndSet(current, next))
                return next;
        }
    }
}
