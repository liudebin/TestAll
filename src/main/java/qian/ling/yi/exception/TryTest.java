package qian.ling.yi.exception;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试异常处理
 * 1. 可以try，但是不 catch Spring中有使用
 * Created by liuguobin on 2016/10/28.
 */
public class TryTest extends AbstractTest {

//    1.
    @Test
    public void testNoCatch() {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        try {
            throw new RuntimeException("测试");
        } finally {
            reentrantLock.unlock();
            logger.info("锁释放处理");
        }
    }

    /**
     * catch中的return 对finally块没有影响
     */
    @Test
    public void testFinally() {
        try {
            throw new RuntimeException("运行时异常");
        } catch (Exception e) {
            logger.info("{}", "catch");
            return;
        } finally {
            logger.info("{}", "finally");
        }
    }


    @Test
    public void test() {
        
    }
}
