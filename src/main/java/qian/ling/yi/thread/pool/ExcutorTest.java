package qian.ling.yi.thread.pool;

import org.junit.Assert;
import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ExcutorTest extends AbstractTest {

    @Test
    public void test() {
        AtomicLong threadIndex = new AtomicLong(0);
        ExecutorService executorService = Executors.newFixedThreadPool(1, r -> new Thread(r, "tt" + threadIndex.getAndIncrement()));
        Future<Integer> future1 = executorService.submit(() -> {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });

        int i = 0;
        try {
            i = future1.get(21000, TimeUnit.MILLISECONDS);

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, i);
    }
}
