package qian.ling.yi.thread;

import org.junit.Test;

/**
 * @author liuguobin
 * @date 2018/5/17
 */

public class ThreadTest {

    @Test
    public void testState() {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                Math.random();
            }
        });
        System. out.println(thread.getState());
        thread.start();
        System. out.println(thread.getState());
        thread.interrupt();
        System. out.println(thread.getState());
    }


    @Test
    public void testJoin() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(20000);
                System.out.println("结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        System.out.println("join begin");
        try {
//            thread.join();
            thread.join(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("join");
    }
}
