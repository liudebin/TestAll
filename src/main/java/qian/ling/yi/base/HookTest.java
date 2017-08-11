package qian.ling.yi.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 钩子测试
 *
 * @author liuguobin
 * @date 2017/8/1
 */

public class HookTest {
    Logger logger = LoggerFactory.getLogger(HookTest.class);

    public void addHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            private volatile boolean hasShutdown = false;
            private AtomicInteger shutdownTimes = new AtomicInteger(0);


            @Override
            public void run() {
                synchronized (this) {
                    logger.info("shutdown hook was invoked, " + this.shutdownTimes.incrementAndGet());
                    if (!this.hasShutdown) {
                        this.hasShutdown = true;
                        long begineTime = System.currentTimeMillis();
                        try {
                            Thread.currentThread().sleep(10000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        controller.shutdown();
                        long consumingTimeTotal = System.currentTimeMillis() - begineTime;
                        logger.info("shutdown hook over, consuming time total(ms): " + consumingTimeTotal);
                    }
                }
            }
        }, "ShutdownHook"));

    }
    public void tesy() {
        addHook();
        System.out.println("435345343453543");
    }

    public void test() {
        tesy();

    }

    public static void main(String[] args) {
        new HookTest().test();
    }
}
