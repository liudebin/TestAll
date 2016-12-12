package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

/**
 * Created by liuguobin on 2016/10/11.
 */
public class EnumTest extends AbstractTest {

    public static enum TEST{
        QIAN("1"),
        LING("2"),
        YI("3");

        String value ;
        private TEST(String value) {
            this.value = value;
        }
        String getValue() {
            return value;
        }
    }

    /**
     * toString返回的是enum对象的名称
     */
    @Test
    public void testToString(){
        logger.info(TEST.QIAN.toString());
        logger.info(TEST.QIAN.name());
    }

    /**
     * 默认传入的参数为name，返回相应的enum对象
     */
    @Test
    public void testValueOf() {
        logger.info(TEST.valueOf("YI").getValue());
        logger.info(TEST.valueOf("YI").toString());
    }
}
