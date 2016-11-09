package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

/**
 * Created by liuguobin on 2016/11/1.
 */
public class WhileTest extends AbstractTest {

    @Test
    public void testBreak() {
        while (true) {
            break;
        }
        logger.info("循环结束");
    }
}
