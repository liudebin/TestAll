package qian.ling.yi.thread.intro.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Semaphore4PermitsCount
 *
 * @author liuguobin
 * @date 2018/5/14
 */

public class Semaphore4PermitsCount {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);

        try {
            semaphore.acquire();
            System.out.println(semaphore.availablePermits());
            System.out.println(semaphore.availablePermits());
            System.out.println(semaphore.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
