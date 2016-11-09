package qian.ling.yi.thread.timer;

import qian.ling.yi.AbstractTest;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer测试用例
 *
 * Created by liuguobin on 2016/10/13.
 */
public class TimerTest extends AbstractTest {
    static int i = 0;


    public static void main(String[] args) {
        Timer timer = new Timer("test");
        timer.schedule(new MyTimerTask(), 1,1000);
    }

}
