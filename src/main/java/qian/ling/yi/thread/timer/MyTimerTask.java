package qian.ling.yi.thread.timer;

import java.util.TimerTask;

/**
 * Created by liuguobin on 2016/10/13.
 */
public class MyTimerTask extends TimerTask {
    static int i = 0;
    @Override
    public void run() {
        System.out.println("执行第" + i++ +"次");
    }
}
