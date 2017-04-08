package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

/**
 * Created by liuguobin on 2017/3/19.
 */
public class LongTest extends AbstractTest {

    /*
    打印的结果是一样的
    String.valueOf() 会先判null，输出null。
    .toString() = Long.toString();
     */
    @Test
    public void testValue() {
        Long l = 1l;
        logger.info("{}", String.valueOf(l));
        logger.info("{}", Long.toString(l));
        logger.info("{}", l.toString());


    }
}
