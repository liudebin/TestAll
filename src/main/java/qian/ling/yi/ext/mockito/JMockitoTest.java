package qian.ling.yi.ext.mockito;

import mockit.Expectations;
import mockit.Mock;
import mockit.Mocked;
import org.junit.Test;
import qian.ling.yi.AbstractTest;

/**
 * TODO
 *
 * @date: 2018/11/13.
 * @author: guobin.liu@holaverse.com
 */

public class JMockitoTest extends AbstractTest {

    @Mocked
    ParamUtil paramUtil;

    @Test
    public void test(){

        new Expectations(ParamUtil.class) {
            {
                ParamUtil.getValue1ByCode("resExecuteSendSwitch");
                result = "N";
////                ParamUtil.getValue2ByCode("resExecuteSendSwitch");
////                result = null;
            }
        };
        System.out.println(ParamUtil.getValue1ByCode("resExecuteSendSwitch"));
        System.out.println(ParamUtil.getValue2ByCode("2"));


    }
}
