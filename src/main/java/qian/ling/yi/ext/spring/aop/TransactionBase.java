package qian.ling.yi.ext.spring.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import qian.ling.yi.AbstractTest;

/**
 * 可以看 有道笔记
 *
 * @date: 2018/10/9.
 * @author: guobin.liu@holaverse.com
 */
@Component
public class TransactionBase extends AbstractTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ApplicationContext applicationContext;

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void test1( int i) {
        insert(i);
    }

    @Transactional
    public void test2( int j) {
        insert(j);
        test1(j+1);
        logger.info("{}", TransactionSynchronizationManager.isSynchronizationActive());
    }

    @Transactional
    public void testExceptionWithTransactional(int j) {
        insert(j);
        throw new RuntimeException("sss");
    }

    public void testExceptionWithoutTransactional(int j) {
        insert(j);
        throw new RuntimeException("sss");
    }

    public void withoutTransactional(int j) {
        insert(j-1);
        testExceptionWithTransactional(j);
    }

    @Transactional
    public void withTransactional(int j) {
        insert(j-1);
        testExceptionWithTransactional(j);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void transactionRequireNew(int i) {
        insert(i);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionRequired(int i) {
        insert(i);
        ((TransactionBase) applicationContext.getBean("transactionBase"))
                .transactionRequireNew(i+1);
        throw new RuntimeException("SSS");
    }







    void insert(int i) {
        jdbcTemplate.execute("insert into rds.tt values (" + i +")");
    }


}
