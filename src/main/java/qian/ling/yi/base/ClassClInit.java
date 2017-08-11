package qian.ling.yi.base;

/**
 * 类的clinit方法会执行几次
 *
 * 只执行一次，
 * 多线程的时候，其他没有获取锁的线程会阻塞，等到那个真正执行的完成，才能走其他流程
 *
 * @author liuguobin
 * @date 2017/6/3
 */

public class ClassClInit {
    public static void main(String[] args) {
        ClassClInit c = new ClassClInit();

        Thread thread1 = new Thread(c::run);
        Thread thread2 = new Thread(c::run);
        thread1.start();
        thread2.start();
    }

    public void run() {
        System.out.println(Thread.currentThread() + "start");
        new ClassClInitBase();
        System.out.println(Thread.currentThread() + "end");
    }
}
