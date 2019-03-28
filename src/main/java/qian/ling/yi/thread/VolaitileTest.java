package qian.ling.yi.thread;

import org.junit.Test;

/**
 * 测试volatile被赋值后，什么样
 *
 * @date: 2019/3/6.
 * @author: guobin.liu@holaverse.com
 */

public class VolaitileTest {


    static volatile  int[] a = {1};
    private static volatile int MY_INT = 0;

    @Test
    public void testInt() throws InterruptedException {
        int i = MY_INT;
        new Thread(() -> MY_INT = 5).start();

        Thread.sleep(5000);
        System.out.println(i);

    }

    @Test
    public void testArray() throws InterruptedException {
        int[] b = a;
        new Thread(() -> a[0]=9).start();

        Thread.sleep(5000);
        System.out.println(b[0]);

    }


    /**
     * 主线程启动2个测试子线程
     *
     * @param args null
     */
    public static void main(String[] args) {
        new ChangeListener().start();
        new ChangeMaker().start();

    }

    /**
     * 此线程负责对MY_INT值改变的侦听，如果有改变就会打印出来
     */
    static class ChangeListener extends Thread {
        @Override
        public void run() {
            int local_value = MY_INT;
            while (local_value < 5) {
                if (local_value != MY_INT) {
                    System.out.println("Got Change for MY_INT : " + MY_INT);
                    local_value = MY_INT;
                }
            }
        }
    }

    /**
     * 此线程负责改变MY_INT的值
     */
    static class ChangeMaker extends Thread {
        @Override
        public void run() {
            int local_value = MY_INT;
            while (MY_INT < 5) {
                System.out.println("Incrementing MY_INT to " + (local_value + 1));
                MY_INT = ++local_value;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
