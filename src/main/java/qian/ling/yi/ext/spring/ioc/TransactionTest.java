package qian.ling.yi.ext.spring.ioc;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import qian.ling.yi.AbstractTest;

/**
 * 事务测试
 *TransactionSynchronizationManager 通过ThreadLocal 保存事务信息
 * @author liuguobin
 * @date 2017/9/4
 */

public class TransactionTest extends AbstractTest {

    @Test
    @Transactional
    public void transA() {
        logger.info("{}", TransactionSynchronizationManager.isActualTransactionActive());
        logger.info("{}", TransactionSynchronizationManager.isCurrentTransactionReadOnly());
        logger.info("{}", TransactionSynchronizationManager.isSynchronizationActive());
        TransactionSynchronizationManager.initSynchronization();
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void flush() {
                System.out.println("flush");
            }
            public void beforeCommit(boolean readOnly) {
                System.out.println(readOnly);
            }
        });
        logger.info("{}", TransactionSynchronizationManager.isSynchronizationActive());
        logger.info("{}", TransactionSynchronizationManager.isCurrentTransactionReadOnly());
        logger.info("{}", TransactionSynchronizationManager.isActualTransactionActive());
    }

    public void transB() {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void flush() {
                System.out.println("flush");
            }
            public void beforeCommit(boolean readOnly) {
                System.out.println(readOnly);
            }
        });
    }
}
