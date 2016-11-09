package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

/**
 * Created by liuguobin on 2016/10/11.
 */
public class EnumTest extends AbstractTest {

    public enum TEST{
        QIAN, LING, YI
    }

    /**
     * toString返回的是enum对象的名称
     */
    @Test
    public void testToString(){
        logger.info(TEST.QIAN.toString());
        logger.info(TEST.QIAN.name());
    }
}
