package qian.ling.yi.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * currentThread，返回的就是当前的线程
 * Created by liuguobin on 2016/11/1.
 */
public class CurrentThreadTest {
    public static Logger logger = LoggerFactory.getLogger(CurrentThreadTest.class);
    public static void main(String[] args) {
        Thread thread1 = new Thread(()->{
            logger.info(Thread.currentThread().getName());
            while(true) {}
        });
        thread1.setName("1");
    Thread thread2 = new Thread(()->{
        logger.info(Thread.currentThread().getName());
            while(true) {}
        });
        thread2.setName("2");
    Thread thread3 = new Thread(()->{
        logger.info(Thread.currentThread().getName());
            while(true) {}
        });
        thread3.setName("3");
    Thread thread4 = new Thread(()->{
        logger.info(Thread.currentThread().getName());
            while(true) {}
        });
        thread4.setName("4");
    Thread thread5 = new Thread(()->{
        logger.info(Thread.currentThread().getName());
            while(true) {}
        });
        thread5.setName("5");
    Thread thread6 = new Thread(()->{
        logger.info(Thread.currentThread().getName());
            while(true) {}
        });
        thread6.setName("6");
    Thread thread7 = new Thread(()->{
        logger.info(Thread.currentThread().getName());
            while(true) {}
        });
        thread7.setName("7");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();

        logger.info(Thread.currentThread().getName());
    }
}
