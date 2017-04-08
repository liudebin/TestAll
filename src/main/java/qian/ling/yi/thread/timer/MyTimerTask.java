package qian.ling.yi.thread.timer;

import java.util.TimerTask;

/**
 *
 * Created by liuguobin on 2016/10/13.
 */
public class MyTimerTask extends TimerTask {
    static int i = 0;
    @Override
    public void run() {
        System.out.println("执行第" + i++ +"次 --   " + System.nanoTime());
        try {
            if (i == 2) {
                Thread.sleep(5000);
            } else {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
