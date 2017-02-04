package qian.ling.yi.what;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

/**
 * try catch with resource 匿名对象不关闭
 * Created by liuguobin on 2016/12/27.
 */
public class AutoCloseTest extends AbstractTest {

    class AutoA implements AutoCloseable {
        AutoB autoB;
        String s;
        AutoA(AutoB autoB, String s){
           this.autoB = autoB;
            this.s = s;
        }

        @Override
        public void close() throws Exception {
            logger.info(s);
        }
    }

    class AutoB implements AutoCloseable {
        String s;
        AutoB(String s) {
            this.s = s;
        }
        @Override
        public void close() throws Exception {
            logger.info(s);
        }
    }

    /**
     * 匿名对象不会自动close
     */
    @Test
    public void testClose() {
        try (AutoA a = new AutoA(new AutoB("B close"), "A close")) {
            logger.info("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 逆序全部关闭
     */
    @Test
    public void testBClose() {
        try (AutoB b = new AutoB("B close");
             AutoA a = new AutoA(b, "A close")) {
            logger.info("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
