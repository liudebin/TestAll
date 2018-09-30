package qian.ling.yi.thread.sychronized;

import java.util.concurrent.TimeUnit;

/**
 * Created by guobin.liu@holaverse.com on 2018/9/27.
 */

public class SynchronizedWaitTest {
    public synchronized void test() {
        System.out.println(" 进入实例锁 ");
        try {
            this.notify();
            this.wait();
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("离开实例锁");
        this.notify();
    }
    public static void main(String[] args) {
        SynchronizedWaitTest syn = new SynchronizedWaitTest();
        Thread thread = new Thread(syn::test);

        thread.setName("test1");
        thread.start();
        try {
            Thread.sleep(1000*3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(syn::test).start();
        try {
            Thread.sleep(1000*3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        syn.notify();


        System.out.println("执行结束");
    }
}
