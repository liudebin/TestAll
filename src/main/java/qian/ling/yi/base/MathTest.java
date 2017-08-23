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

    @Test
    public void test() {
        logger.info("{}", 3&(8-1));
        logger.info("{}", 2<<2);
        logger.info("{}", 3&((2<<12)-1));
        logger.info("{}", 2<<12-1);
        logger.info("{}", (2<<12)-1);
    }
}
