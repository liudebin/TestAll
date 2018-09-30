package qian.ling.yi.thread.sychronized;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

/**
 * Created by guobin.liu@holaverse.com on 2018/9/17.
 */

public class SynchronizedTest {

//    public final Object object = new Object();
    public static synchronized void testClassLock() {
        System.out.println("进入类锁");
        try {
            Thread.sleep(1000*60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("离开类锁");
    }

    public void testObjectLock() {
        synchronized (this) {
            System.out.println(" 进入实例锁 ");
            try {
                Thread.sleep(1000*60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("离开实例锁");
        }

    }

    public static void main(String[] args) {
        SynchronizedTest syn = new SynchronizedTest();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                syn.testObjectLock();
//                SynchronizedTest.testClassLock();
            }
        });

        thread.setName("test1");
        thread.start();

        try {
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        syn.testObjectLock();
//        SynchronizedTest.testClassLock();
        System.out.println("执行结束");
    }

}
