package qian.ling.yi.thread;

import qian.ling.yi.util.DateUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ExecutorsTest
 *
 * @author liuguobin
 * @date 2017/8/4
 */

public class ExecutorsTest {

    public static void main(String[] args) {
        final AtomicLong threadIndex = new AtomicLong(0L);
        ExecutorService executorService = Executors.newFixedThreadPool(2, r -> new Thread(r, "hahhah" + threadIndex.incrementAndGet()));
        executorService.isShutdown();
        for (int i = 0; i < 10; i ++) {

            executorService.execute(() ->{
                try {
                    System.out.println(Thread.currentThread().getName() + " : " + System.nanoTime());
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        System.out.println("executorService already shutdown " + DateUtil.getCurrentDateTime());
        executorService.execute(() ->{
            try {
                System.out.println(Thread.currentThread().getName() + " : " + System.nanoTime());
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

}
