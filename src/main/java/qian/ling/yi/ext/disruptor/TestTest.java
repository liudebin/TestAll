package qian.ling.yi.ext.disruptor;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.concurrent.Executors;

/**
 * test
 *
 * @author liuguobin
 * @date 2017/8/21
 */

public class TestTest extends AbstractTest {
    @Test
    public void test() {
        Disruptor<MyEvent> disruptor = new Disruptor<MyEvent>(new MyEventFactory(), 32, Executors.defaultThreadFactory());
        EventHandler<MyEvent> handler1 = new EventHandler<MyEvent>() {
            /**
             * Called when a publisher has published an event to the {@link RingBuffer}
             *
             * @param event      published to the {@link RingBuffer}
             * @param sequence   of the event being processed
             * @param endOfBatch flag to indicate if this is the last event in a batch from the {@link RingBuffer}
             * @throws Exception if the EventHandler would like the exception handled further up the chainSelf.
             */
            @Override
            public void onEvent(MyEvent event, long sequence, boolean endOfBatch) throws Exception {
                logger.info("handler1: {}, {}, {}", event, sequence, endOfBatch);
            } };
        EventHandler<MyEvent> handler2 = new EventHandler<MyEvent>() {
            /**
             * Called when a publisher has published an event to the {@link RingBuffer}
             *
             * @param event      published to the {@link RingBuffer}
             * @param sequence   of the event being processed
             * @param endOfBatch flag to indicate if this is the last event in a batch from the {@link RingBuffer}
             * @throws Exception if the EventHandler would like the exception handled further up the chainSelf.
             */
            @Override
            public void onEvent(MyEvent event, long sequence, boolean endOfBatch) throws Exception {
                logger.info("handler2: {}, {}, {}", event, sequence, endOfBatch);
            } };
            disruptor.handleEventsWith(handler1);
            disruptor.after(handler1).handleEventsWith(handler2);

        RingBuffer ringBuffer = disruptor.start();
        while (true) {

        }
    }



    class MyEvent {

    }

    class MyEventFactory implements EventFactory<MyEvent> {

        @Override
        public MyEvent newInstance() {
            return new MyEvent();
        }
    }

}
