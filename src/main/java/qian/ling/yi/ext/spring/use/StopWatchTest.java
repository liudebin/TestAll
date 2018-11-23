package qian.ling.yi.ext.spring.use;

import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.springframework.util.StopWatch;
import qian.ling.yi.AbstractTest;

/**
 * StopWatch的test
 *
 * @date: 2018/10/15.
 * @author: guobin.liu@holaverse.com
 */

public class StopWatchTest extends AbstractTest {
    @Test
    public void simple() throws Exception{
        StopWatch sw = new StopWatch();
        sw.start("起床");
        Thread.sleep(1000);
        sw.stop();

        sw.start("洗漱");
        Thread.sleep(2000);
        sw.stop();

        sw.start("锁门");
        Thread.sleep(500);
        sw.stop();

                logger.info("{}", sw.prettyPrint());
        logger.info("{}", sw.getTotalTimeMillis());
        logger.info("{}", sw.getLastTaskName());
        logger.info("{}", sw.getLastTaskInfo());
        logger.info("{}", sw.getTaskCount());
    }
}