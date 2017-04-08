package qian.ling.yi.thread.timer;

import qian.ling.yi.AbstractTest;

import java.util.Timer;

/**
 * Timer测试用例
 * 1.schedule 注重任务执行的间隔
 *  某次延迟了，也会按固定的间隔执行。 TBschedual 使用的就是 这个。
 * 2.scheduleAtFixedRate 注重任务执行的次数，频率
 *  每一次 执行这个task的计划执行时间在最初就被定下来了，
 *  某次如果延迟了，则可能会直接执行下一次。为了保证确定好的频率
 *
 *
 * 每一个Timer仅对应唯一一个线程。
 * Timer不保证任务执行的十分精确。
 * Timer类的线程安全的。
 *
 * Created by liuguobin on 2016/10/13.
 */
public class TimerTest extends AbstractTest {
    static int i = 0;


    public static void main(String[] args) {
        Timer timer = new Timer("test");

//        1.
// time为Date类型：在指定时间执行一次。
//        timer.schedule(new MyTimerTask(), new Date());
//        2.
// firstTime为Date类型,period为long
// 从firstTime时刻开始，每隔period毫秒执行一次。
//        timer.schedule(task, firstTime, period);
//        timer.schedule(new MyTimerTask(), 2000,3000); 下次也是固定时间间隔

        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 3000);//下次会减少间隔，以靠近之前确定的时间
    }

}
