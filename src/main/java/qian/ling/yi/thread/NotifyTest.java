package qian.ling.yi.thread;

import qian.ling.yi.AbstractTest;

/**
 * Created by liuguobin on 2016/11/18.
 */
public class NotifyTest extends AbstractTest{


    public static void main(String[] args) throws Exception{
        Object o = new Object();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
                try {
                    o.wait(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        while (true) {
            o.notifyAll();
            System.out.println("notify");
        }
    }
}
