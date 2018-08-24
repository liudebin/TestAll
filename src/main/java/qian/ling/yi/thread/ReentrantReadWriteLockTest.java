package qian.ling.yi.thread;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock
 *
 * @author liuguobin
 * @date 2018/5/25
 */

public class ReentrantReadWriteLockTest {

    @Test
    public void testBase() {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.readLock().lock();
        System.out.println("获取读锁");
//        readWriteLock.writeLock().lock();// 无法获取读锁
//        System.out.println("获取写锁");

        readWriteLock.readLock().lock();
        readWriteLock.readLock().unlock();
        readWriteLock.readLock().unlock();
        readWriteLock.writeLock().lock();// 无法获取读锁
        System.out.println("获取写锁");

    }

    @Test
    public void testUp() {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.writeLock().lock();
        readWriteLock.readLock().lock();
        readWriteLock.writeLock().unlock();
        System.out.println("锁升级完成");
    }

    static final class ThreadLocalHoldCounter
            extends ThreadLocal {
    }
}
