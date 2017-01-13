package qian.ling.yi.base;

import qian.ling.yi.AbstractTest;

/**
 * Created by liuguobin on 2017/1/12.
 */
public class IFTest extends AbstractTest {

    public boolean testFalse() {
        boolean a = false;
        return returnTrue() && returnFalse() && returnTrue();
    }

    public boolean returnFalse() {
        logger.info("return false");
        return false;
    }
    public boolean returnTrue() {
        logger.info("return true");
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new IFTest().testFalse());
    }
}
