package qian.ling.yi.thread.intro.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author liuguobin
 * @date 2018/5/14
 */

public class Semaphore7Queue {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);

        try {
            semaphore.acquire();
            System.out.println(semaphore.getQueueLength());
            System.out.println(semaphore.hasQueuedThreads());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //此处释放了！！！
            semaphore.release();
        }
        semaphore.acquire();
        System.out.println(semaphore.getQueueLength());
        System.out.println(semaphore.hasQueuedThreads());
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.setName(i+ "");
            thread.start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(semaphore.getQueueLength());
        System.out.println(semaphore.hasQueuedThreads());
    }
}
