package qian.ling.yi.thread;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuguobin on 2016/10/25.
 */
public class ReentrantLockTest extends AbstractTest{


    /**
     * 获取多少次锁，就要释放多少次
     * @param args
     */
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        System.out.println("尝试获取锁");
        lock.lock();
        System.out.println("锁获取成功1");

        lock.lock();
        System.out.println("锁获取成功2");

        lock.lock();
        System.out.println("锁获取成功3");

        lock.lock();
        System.out.println("锁获取成功4");

        lock.unlock();
        System.out.println("释放锁4");
        lock.unlock();
        System.out.println("释放锁3");
        lock.unlock();
        System.out.println("释放锁2");
//        lock.unlock();
//        System.out.println("释放锁1");

        new Thread(()->{
            System.out.println("尝试获取锁");
            lock.lock();
            System.out.println("锁获取成功");
        }).start();
        new Thread(()->{
            System.out.println("尝试获取锁");
            lock.lock();
            System.out.println("锁获取成功");
        }).start();

    }

}
