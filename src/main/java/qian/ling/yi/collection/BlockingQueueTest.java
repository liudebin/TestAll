package qian.ling.yi.collection;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueueTest
 *
 * @author liuguobin
 * @date 2018/8/20
 */

//固定大小的 线性表
public class BlockingQueueTest {
    @Test
    public void test() {
        BlockingQueue<String> b = new ArrayBlockingQueue<>(1);
        b.add("1");
        b.add("2");
        System.out.println(b.size());
//        java.lang.IllegalStateException: Queue full
    }
}
