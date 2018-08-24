package qian.ling.yi.thread.intro.cyclicBarrier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 测试CyclicBarrier
 *
 * Created by liuguobin on 2018/05/18.
 */
public class CyclicBarrier1Await {

    static Logger logger = LoggerFactory.getLogger(CyclicBarrier1Await.class);

    public static void main(String[] args) {
        //设置需要到达的同行者数量
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        Runnable run = () -> {
            try {
                logger.info("{}", "休眠了");
                Thread.sleep(10000);
                logger.info("{}", "休眠结束");
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        for (int i = 0; i < 2; i++) {
           new Thread(run).start();
        }

        try {
            logger.info("要到齐了");
            cyclicBarrier.await();
            logger.info("{}", 3);
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}
