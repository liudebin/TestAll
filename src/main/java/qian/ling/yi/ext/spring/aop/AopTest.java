package qian.ling.yi.ext.spring.aop;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import qian.ling.yi.ext.spring.bootstrap.AbstractContextLoaderTest;

/**
 * TODO
 *
 * @date: 2018/10/9.
 * @author: guobin.liu@holaverse.com
 */

public class AopTest extends AbstractContextLoaderTest {
    @Autowired
    TransactionBase transactionBase;

    @Test
    public void test() {
        transactionBase.test1();
        transactionBase.test2();
    }
}
