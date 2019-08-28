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

    @Autowired
    TransactionBase1 transactionBase1;

    @Test
    public void testException(){
        transactionBase.testExceptionWithoutTransactional(404);
    }

    @Test
    public void testExceptionWithTransactional(){
        transactionBase.testExceptionWithTransactional(404);
    }

    @Test
    public void aToBWithoutTransactional(){
        transactionBase.withoutTransactional(404);
    }

    @Test
    public void aToBWithTransactional(){
        transactionBase.withTransactional(404);
    }
    @Test
    public void aToBWithTransactionalRequiedNew(){
        transactionBase.transactionRequired(404);
    }


    @Test
    public void test() {
        transactionBase.test1(1);
        transactionBase.test2(2);
    }


    @Test
    public void test2(){
        transactionBase1.test2(2);
    }
}
