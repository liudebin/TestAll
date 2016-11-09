package qian.ling.yi.ext.dom4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuguobin on 2016/9/21.
 */
public class TestException {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void testExceptionObject() {
        try {
            throw new Exception("自定义异常");
        } catch (Exception e) {
            logger.info("[异常信息][{}]", e.getMessage());
            logger.info("[异常信息]", e);
        }
    }
}
