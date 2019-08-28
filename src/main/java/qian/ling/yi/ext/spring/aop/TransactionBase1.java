package qian.ling.yi.ext.spring.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import qian.ling.yi.AbstractTest;

/**
 * TODO
 *
 * @date: 2018/10/9.
 * @author: guobin.liu@holaverse.com
 */
@Component
public class TransactionBase1 extends AbstractTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TransactionBase transactionBase;

    @Transactional
    public void test2( int j) {
        insert(j);
        transactionBase.test1(j+1);
        logger.info("{}", TransactionSynchronizationManager.isSynchronizationActive());
    }

    void insert(int i) {
        jdbcTemplate.execute("insert into rds.tt values (" + i +")");
    }


}
