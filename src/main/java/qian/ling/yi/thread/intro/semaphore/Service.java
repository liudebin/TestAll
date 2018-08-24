package qian.ling.yi.thread.intro.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author liuguobin
 * @date 2018/5/7
 */

public interface Service {
    default void testMethod(Semaphore semaphore) {
        try {
            semaphore.acquire();
//            semaphore.acquireUninterruptibly();
            System.out.println(Thread.currentThread().getName()
                    + " begin timer=" + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()
                    + "   end timer=" + System.currentTimeMillis());
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
