package qian.ling.yi.jdk8;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

/**
 * Created by liuguobin on 2016/10/25.
 */
public class LambdaTest extends AbstractTest {

    @Test
    public void testThread() {
        new Thread(() -> {
            logger.info("执行了……");
        }).run();
    }
}
