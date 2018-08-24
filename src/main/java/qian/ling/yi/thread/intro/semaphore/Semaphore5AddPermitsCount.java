package qian.ling.yi.thread.intro.semaphore;

import java.util.concurrent.Semaphore;

/**
 * addPermitsCount
 *
 * @author liuguobin
 * @date 2018/5/14
 */

public class Semaphore5AddPermitsCount {
    public static void main(String[] args) {
        try {
            Semaphore semaphore = new Semaphore(5);
            semaphore.acquire();
            semaphore.acquire();
            semaphore.acquire();
            semaphore.acquire();
            semaphore.acquire();
            System.out.println(semaphore.availablePermits());
            semaphore.release();
            semaphore.release();
            semaphore.release();
            semaphore.release();
            semaphore.release();
            semaphore.release();
            System.out.println(semaphore.availablePermits());
            semaphore.release(4);
            System.out.println(semaphore.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
