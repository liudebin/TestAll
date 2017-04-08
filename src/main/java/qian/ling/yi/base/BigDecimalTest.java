package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.math.BigDecimal;

/**
 * Created by liuguobin on 2016/11/3.
 */
public class BigDecimalTest extends AbstractTest {

    /**
     * 简单的说，就是值相等，就等
     * 还没仔细读源码 存疑
     */
    @Test
    public void testEqual() {
        BigDecimal bigDecimala = new BigDecimal(0);
        BigDecimal bigDecimalb = new BigDecimal(0);
        BigDecimal bigDecimalc = new BigDecimal(0L);
        logger.info("{}", bigDecimala.equals(bigDecimalb));
        logger.info("{}", bigDecimala.equals(bigDecimalc));
        logger.info("{}", bigDecimala.equals(0));
        logger.info("{}", bigDecimala.equals(null));
//        logger.info("{}", bigDecimala.compareTo(null)); 异常
        logger.info("{}", bigDecimala.compareTo(bigDecimala));
        logger.info("{}", bigDecimala.compareTo(bigDecimalb));
        logger.info("{}", bigDecimala.compareTo(new BigDecimal(1)));
        logger.info("{}", bigDecimala.compareTo(new BigDecimal(-1)));
    }

    @Test
    public void testAdd() {
        BigDecimal bigDecimal = new BigDecimal(1).add(new BigDecimal(1));
        logger.info("值{}", bigDecimal);
    }

    @Test
    public void test() {
        BigDecimal amount = new BigDecimal(99);
        BigDecimal rate = new BigDecimal(1);
        BigDecimal divisor = new BigDecimal(100).add(rate);
        BigDecimal fee = rate.multiply(amount);
        logger.info("{}", fee);
        fee = fee.divide(divisor, 2, BigDecimal.ROUND_HALF_UP);
        logger.info("{}", fee);
    }

    @Test
    public void testCompareTo() {
        BigDecimal amount = new BigDecimal(-1);
//        BigDecimal amount = new BigDecimal(0);//0
//        BigDecimal amount = new BigDecimal(1);//1
//        BigDecimal amount = null;//0
        logger.info("{}", testMethod( amount));

    }
    private BigDecimal testMethod(BigDecimal amount) {
        if (null == amount || BigDecimal.ZERO.compareTo(amount) >= 0) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.ONE;
    }

    public void newTest() {
        BigDecimal bigDecimal = new BigDecimal("123312");
    }
}
