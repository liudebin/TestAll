package qian.ling.yi.thread.intro.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author liuguobin
 * @date 2018/5/14
 */

public class Semaphore8Fair {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0,true);
//        Semaphore semaphore = new Semaphore(0);


        for (int i = 0; i < 10; i++) {
            Thread firstThread = new Thread(() -> {
                try {
                    System.out.println("ThreadName=" + Thread.currentThread().getName() + "启动了");
                    semaphore.acquire();
                    System.out
                            .println("ThreadName=" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
            firstThread.setName(i + "");
            firstThread.start();
        }
        semaphore.release();
//        for (int i = 0; i < 10; i++) {
//            semaphore.release();
//        }

    }
}
