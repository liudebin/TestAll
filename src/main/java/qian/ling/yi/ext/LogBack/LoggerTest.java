package qian.ling.yi.ext.LogBack;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

/**
 * Created by liuguobin on 2016/10/9.
 */
public class LoggerTest extends AbstractTest{

    /**
     * 打印null
     *
     * hhanull
     */
    @Test
    public void testLoggerNull() {
        String h = null;
        logger.info("hha{}", h);
    }
}
