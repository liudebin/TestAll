package qian.ling.yi.thread.sourceRead;

import org.junit.Test;

/**
 * AQT
 *
 * @author liuguobin
 * @date 2018/5/12
 */

public class AQSTest {
//    class Node {
//        Node pre;
//        String v;
//
//        @Override
//        public String toString() {
//
//            return v + ":" + pre;
//        }
//    }
//
//
//    @Test
//    public void testT() {
//        Node  t = new Node();
//        t.v = "t";
//        Node pred = new Node();
//        pred.pre = t;
//        pred.v = "pred";
//
//        Node node = new Node();
//        node.pre = pred;
//        node.v = "node";
//
//        System.out.println(t);
//        System.out.println(pred);
//        System.out.println(node);
//
//        node.pre = pred = pred.pre;
//
//        System.out.println(t);
//        System.out.println(pred);
//        System.out.println(node);
//    }

    @Test
    public void testEnq() {
        AbstractQueuedSynchronizer a = new AbstractQueuedSynchronizer() {
        };
        AbstractQueuedSynchronizer.Node node = new AbstractQueuedSynchronizer.Node();
        node.thread = Thread.currentThread();
        System.out.println(node);
        final AbstractQueuedSynchronizer.Node n = a.enq(node);
        System.out.println(node.equals(n));
        System.out.println(n);


    }

}
