package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

/**
 * MathTest
 *
 * @author liuguobin
 * @date 2017/8/11
 */

public class MathTest extends AbstractTest {
    static final int SHARED_SHIFT   = 16;
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
    static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;




    // threadLocal
    private static final int HASH_INCREMENT = 0x61c88647;

    @Test
    public void testThreadLocal() {
        logger.info("{}", HASH_INCREMENT);
    }
    @Test
    public void test1() {
        logger.info("{}", SHARED_SHIFT);
        logger.info("{}", SHARED_UNIT);
        logger.info("{}", MAX_COUNT);
        logger.info("{}", EXCLUSIVE_MASK);
    }

    // & 什么运算 位与运算

    @Test
    public void test() {
        logger.info("{}", 3&(8-1));
        logger.info("{}", 3&1);
        logger.info("{}", 3&2);
        logger.info("{}", 3&5);
        logger.info("{}", 3&8);
        logger.info("{}", 3|8);
        logger.info("{}", 3|7);
        logger.info("{}", 2<<2);
        logger.info("{}", 4<<1);
        logger.info("{}", 1<<4);
        logger.info("{}", 1>>4);
        logger.info("{}", 1>>>4);
        logger.info("{}", -1>>4);
        logger.info("{}", -1>>>4);
        logger.info("{}", 2<<3);
        logger.info("{}", 1<<1);
        logger.info("{}", 3&((2<<12)-1));
        logger.info("{}", 2<<12-1);
        logger.info("{}", (2<<12)-1);
    }

    //>> 保留符号偏移
    // >>> 无符号偏移，最高位都是补0
    @Test
    public void testNoSee() {
        logger.info("{}", 8>>1);
        logger.info("{}", 8>>>1);
        logger.info("{}", 8>>>2);
        logger.info("{}", 8>>>3);
        logger.info("{}", 8>>>4);
        logger.info("{}", 8>>>5);
        logger.info("{}", 16>>>2);
    }


    @Test
    public void or() {
        logger.info("{}", 7|1);
        logger.info("{}", 8|1);
        logger.info("{}", 8|2);
        logger.info("{}", 8|3);
    }

    @Test
    public void testNo() {
        logger.info("{}", ~7);
    }

    @Test
    public void testXOR() {
        logger.info("{}", 2^3);
        logger.info("{}", 2^4);
        logger.info("{}", 2^5);
    }

    @Test
    public void testLong() {
//        int i = 2147483647;
        System.out.println(Integer.MAX_VALUE);
    }

}
