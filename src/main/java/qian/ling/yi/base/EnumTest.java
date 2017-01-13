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
        try {
            TEST.valueOf("HAHA");
        } catch (Exception e) {
            logger.info("无值");
        }

        logger.info(TEST.valueOf("HAHA").toString());
    }

    @Test
    public void testSpeed() {
//        long s = System.nanoTime();
//        for (int i = 1; i < 100000; i ++) {
//            notContain("YI");
//        }
        long s1 = System.nanoTime();
//        System.out.println(s1-s);
//
        for (int i = 1; i < 100000; i ++) {
            notMapContain("YI");
        }
        long s2 = System.nanoTime();
        System.out.println(s2-s1);
//        for (int i = 1; i < 100000; i ++) {
//            notContain("YI");
//        }
//        long s3 = System.nanoTime();
//        System.out.println(s3-s2);
//
//        for (int i = 1; i < 100000; i ++) {
//            notMapContain("YI");
//        }
//        long s4 = System.nanoTime();
//        System.out.println(s4-s3);

//        System.out.println("颠倒顺序");
//
//        long s4 = System.nanoTime();
//        for (int i = 1; i < 100000; i ++) {
//            notMapContain("YI");
//        }
//        long s5 = System.nanoTime();
//        System.out.println(s5-s4);
//        for (int i = 1; i < 100000; i ++) {
//            notContain("YI");
//        }
//
//        long s6 = System.nanoTime();
//        System.out.println(s6-s5);
//
//        for (int i = 1; i < 100000; i ++) {
//            notMapContain("YI");
//        }
//
//        long s7 = System.nanoTime();
//        System.out.println(s7-s6);
//
//        for (int i = 1; i < 100000; i ++) {
//            notContain("YI");
//        }
//        long s8 = System.nanoTime();
//        System.out.println(s8-s7);

    }

    private boolean notContain(String payType) {

        try {
            TEST.valueOf(payType);
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private boolean notMapContain(String test) {
        for (TEST test1 : TEST.values()) {
            if (test1.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

}
