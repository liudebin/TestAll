package qian.ling.yi.thread.intro.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author liuguobin
 * @date 2018/5/7
 */

public class Semaphore3AcquireRelease {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);
        Service service = new Service() {
            @Override
            public void testMethod(Semaphore semaphore) {
                try {
                    semaphore.acquire(2);
                    System.out.println(Thread.currentThread().getName()
                            + " begin timer=" + System.currentTimeMillis());
                    int sleepValue = ((int) (Math.random() * 10000));
                    System.out.println(Thread.currentThread().getName() + " 停止了"
                            + (sleepValue / 1000) + "秒");
                    Thread.sleep(sleepValue);
                    System.out.println(Thread.currentThread().getName()
                            + "   end timer=" + System.currentTimeMillis());
                    semaphore.release(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        Thread[] a = new Thread[10];
        for (int i = 0; i < 10; i++) {
            a[i] = new Thread(() -> service.testMethod(semaphore));
            a[i].start();
        }

    }
}
