package qian.ling.yi.ext.disruptor.office;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * LongEventMain
 *
 * @author liuguobin
 * @date 2017/8/10
 */

public class LongEventMain {
    public static void main(String[] args) throws Exception
    {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();

        // The factory for the event
        LongEventFactory factory = new LongEventFactory();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        // Construct the Disruptor
//        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, executor);
//        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, executor);
//        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, Executors.defaultThreadFactory());
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, Executors.privilegedThreadFactory());

        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler());

        // Start the Disruptor, starts all threads running
        RingBuffer<LongEvent> ringBuffer =disruptor.start();

//        LongEventProducer producer = new LongEventProducer(ringBuffer);
        LongEventProducerWithTranslator translator = new LongEventProducerWithTranslator(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++)
        {
            bb.putLong(0, l);
            translator.onData(bb);
            Thread.sleep(1000);
        }
    }


}
