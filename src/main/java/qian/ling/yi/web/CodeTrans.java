package qian.ling.yi.web;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

/**
 * Created by liuguobin on 2016/9/28.
 */
public class CodeTrans extends AbstractTest{
    @Test
    public void testUnicode() throws Exception{
        logger.info(new String("\\u4ea4\\u6613\\u6210\\u529f".getBytes(),"UTF-8"));
    }
}
