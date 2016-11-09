package qian.ling.yi.thread;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuguobin on 2016/10/25.
 */
public class CountDownLatchTest extends AbstractTest{

    /**
     * 计数器，用来控制线程
     * 传入参数2，表示计数器计数为2
     */
    private final static CountDownLatch mCountDownLatch = new CountDownLatch(2);

    @Test
    public void testBasic() throws Exception{
        new Thread(new BasicThread()).start();
        new Thread(new BasicThread()).start();
        mCountDownLatch.await(1, TimeUnit.SECONDS);//等一秒，不管结果，继续执行
        logger.info("运行结束");
    }



    class BasicThread implements Runnable{
        @Override
        public void run() {
            logger.info("还需要执行：{}个线程", mCountDownLatch.getCount());
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mCountDownLatch.countDown();
            logger.info("还需要执行：{}个线程", mCountDownLatch.getCount());
        }
    }
}
