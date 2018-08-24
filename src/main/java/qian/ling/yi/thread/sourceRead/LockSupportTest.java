package qian.ling.yi.thread.sourceRead;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupportTest
 *
 * @author liuguobin
 * @date 2018/5/11
 */

public class LockSupportTest {


    public static void main(String[] args) {
        Thread thread = new Thread(()-> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("before unPark");
            LockSupport.unpark(Thread.currentThread());
            System.out.println("after unPark");
            LockSupport.park();
        });
//        LockSupport.unpark(Thread.currentThread());
        System.out.println( "before park" );
        thread.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.park( "Park" );
        // 等待获取许可
        System.out.println( "after park" );

//        Thread thread = Thread.currentThread();
//        LockSupport.unpark(thread);//释放许可
//        LockSupport.park();// 获取许可
//        System.out.println("b");
    }


    //

    @Test
    public void testInterrupt() throws InterruptedException {
        Thread thread = new Thread(() ->{
            LockSupport.park();
            System.out.println("释放");
        });
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }


    @Test
    public void testUnpark() {
        Object lockObject = new Object();
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(() ->{
                LockSupport.park(lockObject);
                System.out.println(Thread.currentThread().getName());
            });
            thread.setName(i + "");
            thread.start();
            threads[i] = thread;
        }

        for (Thread thread : threads) {
            LockSupport.unpark(thread);
        }
    }
}
