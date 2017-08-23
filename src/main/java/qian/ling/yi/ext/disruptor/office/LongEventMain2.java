package qian.ling.yi.ext.disruptor.office;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * LongEventMain
 *
 * @author liuguobin
 * @date 2017/8/10
 */

public class LongEventMain2 {
    static Logger logger = LoggerFactory.getLogger(LongEventMain2.class);

    public static void main(String[] args) throws Exception
    {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 8;

        // Construct the Disruptor
//        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, executor, ProducerType.SINGLE, new YieldingWaitStrategy());
        Disruptor<LongEvent> disruptor =
                new Disruptor<>(LongEvent::new, bufferSize, r -> {
                    logger.info("?????");
                    return new Thread(r);
                }
//                        ProducerType.SINGLE,
//                        new YieldingWaitStrategy()
                );

        // Connect the handler
        EventHandler a = (event, sequence, endOfBatch) -> {
            TimeUnit.MILLISECONDS.sleep(10000);
            logger.info("Event: {}" ,((LongEvent) event).get());

        };
        disruptor.handleEventsWith(a);
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> logger.info("Event1: {}" ,event.get()));

        disruptor.after(a).handleEventsWith((event, sequence, endOfBatch) -> logger.info("Event2: {}" ,((LongEvent) event).get()));

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
//        ringBuffer.


        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; l < 30; l++)
        {
            bb.putLong(0, l);
            ringBuffer.publishEvent((event, sequence, buffer) -> event.set(buffer.getLong(0)), bb);
            logger.info("放入值  {}", l);
            Thread.sleep(1000);
        }
        disruptor.shutdown();
    }
}
