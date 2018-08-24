package qian.ling.yi.thread.intro.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author liuguobin
 * @date 2018/5/16
 */

public class Exchange<aa> {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<String>();
        Thread a = new Thread(() -> {
            try {
                System.out.println("在线程A中得到线程B的值=" + exchanger.exchange("中国人A"));
                System.out.println("A end!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        a.start();
        System.out.println("a start!");

        Thread b = new Thread(() -> {
            try {
                System.out.println("在线程B中得到线程A的值=" + exchanger.exchange("中国人B"));
                System.out.println("B end!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        b.start();



        Thread c = new Thread(() -> {
            try {
                System.out.println("在线程c中得值=" + exchanger.exchange("中国人C", 10, TimeUnit.SECONDS));
                System.out.println("B end!");
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
        });
        c.start();
        System.out.println("main end!");
    }

    public aa enen(aa obj) {
        return null;
    }
}
