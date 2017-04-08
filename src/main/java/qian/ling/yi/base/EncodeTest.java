package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

/**
 * Created by liuguobin on 2017/1/18.
 */
public class EncodeTest extends AbstractTest {

    @Test
    public void testGBK() throws Exception {
//        String s = "3005215101201701030016212461350000000139000000010000156王帅                                                        148407发送红包,金额:10000                                                                                                                                                                                                                       \n";
//        String s = "3005215101201701030016212461350000000741000097048023156李晶晶                                                      457436发送红包,金额:97048023                                                                                                                                                                                                                    ";
        String s = "300415363520160504001610600195410107855 000000000010156李晶晶                                                       发送红包,金额:10000                                                                                                                                                                                                                               \n";
        logger.info("{}", s.length());
        s = new String(s.getBytes("UTF-8"), "GBK");
        logger.info("{}，{}", s.length(), s);
        logger.info("{}", new String(s.getBytes("GBK"), "GBK").length());
    }

}
