package qian.ling.yi.thread.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.*;

/**
 *线程池自己跑一下 - 纯运算设置cpu相关的才有意义
 * 只要有超过50ms耗时，则线程数就跟cpu的关系不是很大了 10-50 ms 在4核的情况下 200-300 即可。
 * 而只有cpu运算的，与cpu保持一致的线程数是最优的，此处cpu运算就是一些运算。
 */
public class ThreadPoolConfigTest {
    private static final Logger log = LoggerFactory.getLogger(ThreadPoolConfigTest.class);

    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong(0);
        System.out.println(Runtime.getRuntime().availableProcessors());
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(50, 50, 300, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(50),
                new ThreadFactoryBuilder().setNameFormat("prime-console-biz-executor-%d").build(),
                new ThreadPoolExecutor.CallerRunsPolicy()
//                new ThreadPoolExecutor.DiscardOldestPolicy() {
//                    @Override
//                    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
//                        log.info("bizTaskExecutorReject {} ", atomicLong.getAndIncrement());
//                        super.rejectedExecution(r, e);
//                    }
//                }

                );

        long l = System.nanoTime();

        for (int i = 0; i < 10000; i++) {
            if (i%500 == 0) {
                long taskCount = threadPoolExecutor.getTaskCount();
                long poolSize = threadPoolExecutor.getPoolSize();
                long completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
                long activeCount = threadPoolExecutor.getActiveCount();
                long queueSize = threadPoolExecutor.getQueue().size();
                log.info(
                        "taskCount=>{}, poolSize=>{}, completedTaskCount=>{}, activeCount=>{}, queueSize=>{}",
                         taskCount, poolSize, completedTaskCount, activeCount, queueSize);
            }
            threadPoolExecutor.execute(()-> {
                Random random = new Random();
                Long d = 0L;
                for (int i1 = 0; i1 < 1000; i1++) {
                    d = d + random.nextInt() ;
                }
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            });
        }

        System.out.println(System.nanoTime() - l);
    }
}