package qian.ling.yi.thread.sourceRead;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLockTest
 *
 * @author liuguobin
 * @date 2018/5/30
 */

public class StampedLockTest {

    public static void main(String[] args) {
        StampedLock stampedLock = new StampedLock();
        Integer i = 0;
        new Thread(() -> {
            long stamp = stampedLock.tryOptimisticRead();
            if (!stampedLock.validate(stamp)) { // 检查乐观读锁后是否有其他写锁发生
                stamp = stampedLock.readLock(); // 获取一个悲观读锁
                try {

                } finally {
                    stampedLock.unlockRead(stamp); // 释放悲观读锁
                }
            }
        }).start();

        new Thread(() ->{
            long l = stampedLock.writeLock();

                stampedLock.unlock(l);

        }).start();



    }
}
