package qian.ling.yi.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import qian.ling.yi.AbstractTest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 测试CyclicBarrier
 *
 * Created by liuguobin on 2016/10/27.
 */
public class CyclicBarrierTest {

    static Logger logger = LoggerFactory.getLogger(CyclicBarrierTest.class);

    public static void main(String[] args) {
        //构造函数中的线程，肯定是在满足条件后第一个执行
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            logger.info("最先执行");
        });
        logger.info("{}", cyclicBarrier.getParties()); // 要求达到条件的await，在构造时设定的3 可以通过
        logger.info("waitingNum : {}", cyclicBarrier.getNumberWaiting());//当前在已经在等待的数量

        Thread thread = new Thread(() -> {
            try {
                cyclicBarrier.await();
//                Thread.sleep(10000);
                logger.info("{}", 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        不是即时中断，不起作用
        thread.interrupt();

        logger.info("waitingNum : {}", cyclicBarrier.getNumberWaiting());

        Thread thread1 = new Thread(() -> {

            try {
                cyclicBarrier.await();
//                Thread.sleep(10000);
                logger.info("{}", 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("waitingNum : {}", cyclicBarrier.getNumberWaiting());

//        for(int i = 0; i < 4; i ++) {
//            cyclicBarrier.reset(); //直接这样写，之前调用的await()方法都会抛出异常，可以在执行时中断的时候试一下
//            logger.info("重置了");
//        logger.info("waitingNum : {}", cyclicBarrier.getNumberWaiting());
//            thread.start();
//            thread1.start();
//        }
        try {
            cyclicBarrier.await();
            logger.info("{}", cyclicBarrier.getNumberWaiting());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

//        CyclicBarrier的阻塞线程被中断时，才会为true；
        logger.info("{}", cyclicBarrier.isBroken());
        if (cyclicBarrier.isBroken()) {
            cyclicBarrier.reset(); //直接这样写，之前调用的await()方法都会抛出异常，可以在执行时中断的时候试一下
            logger.info("重置，之后要求等的数量：{}", cyclicBarrier.getParties());
            logger.info("waitingNum : {}", cyclicBarrier.getNumberWaiting());

            await(cyclicBarrier);
            await(cyclicBarrier);
            await(cyclicBarrier);
            logger.info("{}", "结束了");
        }
    }

    static void await(CyclicBarrier cyclicBarrier) {
        new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

    }


}
