package qian.ling.yi.ext.vavr;

import io.vavr.Tuple2;
import io.vavr.collection.Queue;
import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class QueueTest {

    @Test
    public void testDequeue() {
        Queue<Integer> que = Queue.of(1, 2, 3);
        System.out.println(que);
        Tuple2<Integer, Queue<Integer>> dequeue = que.dequeue();
        System.out.println(dequeue._1);
        System.out.println(dequeue._2);
    }

    @Test
    public void testDequeueOp() {
        Queue<Integer> que = Queue.of(1);
        System.out.println(que);
        Tuple2<Integer, Queue<Integer>> dequeue = que.dequeue();
        Queue<Integer> emptyQueue = dequeue._2;
        System.out.println(dequeue._1);
        System.out.println(emptyQueue);
        Assertions.assertThrows(NoSuchElementException.class, emptyQueue::dequeue, "not throwExceptions");

        Option<Tuple2<Integer, Queue<Integer>>> tuple2s = emptyQueue.dequeueOption();

    }
}
