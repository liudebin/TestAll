package qian.ling.yi.thread.intro.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author liuguobin
 * @date 2018/5/14
 */

public class Semaphore9TryAcquire {

    public static void main(String[] args) throws InterruptedException {
//        Semaphore semaphore = new Semaphore(1,true);
        Semaphore semaphore = new Semaphore(1);


        for (int i = 0; i < 2; i++) {
            Thread firstThread = new Thread(() -> {
//                if (semaphore.tryAcquire()) {
                try {
                    if (semaphore.tryAcquire(10, TimeUnit.SECONDS)) {
                        System.out.println("ThreadName=" + Thread.currentThread().getName()
                                + "首选进入！");
                        semaphore.release();
                    } else {
                        System.out.println("ThreadName=" + Thread.currentThread().getName()
                                + "未成功进入！");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            firstThread.setName(i + "");
            firstThread.start();

            Thread.sleep(5000);
        }

    }
}
