package qian.ling.yi.ext.spring.ioc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import qian.ling.yi.ext.spring.ioc.bootstrap.AbstractContextLoader;

/**
 * 测试抽象需要注入对象，不用设置抽象类为@component，只要让子类为component即可。
 * Created by liuguobin on 2016/12/10.
 */
public class TestIOC extends AbstractContextLoader{

    @Autowired
    ImplIoc implIoc;
    @Test
    public void testIoc() {
        implIoc.test();
    }
}
