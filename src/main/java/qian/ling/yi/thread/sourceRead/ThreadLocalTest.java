package qian.ling.yi.thread.sourceRead;

import org.junit.Test;

/**
 * @author liuguobin
 * @date 2018/5/25
 */

public class ThreadLocalTest {
    ThreadLocal local = new ThreadLocal();
    ThreadLocal local1 = new ThreadLocal();

    @Test
    public void testSet() {
        ThreadLocalTest test = new ThreadLocalTest();
        Thread A = new Thread(()->{
            test.local.set("1000");
            test.local1.set("1001");
            System.out.println(Thread.currentThread().getName() + ":" + test.local.get());
            System.out.println(Thread.currentThread().getName() + ":" + test.local1.get());
        });
        A.setName("A");
        A.start();
        test.local.set(100);
        test.local1.set(101);
        System.out.println(Thread.currentThread().getName() + ":" + test.local.get());
        System.out.println(Thread.currentThread().getName() + ":" + test.local1.get());
    }


    @Test
    public void get() {
        final Object o = local.get();
        System.out.println(o);
    }

    public static void main(String[] args) {
        long l1 = (long) ((1L << 31) * (Math.sqrt(5) - 1));
        System.out.println("as 32 bit unsigned: " + l1);
        int i1 = (int) l1;
        System.out.println("as 32 bit signed:   " + i1);
        System.out.println("MAGIC = " + 0x61c88647);

        System.out.println(0x61c88647 & 15);
        System.out.println((0x61c88647 >> 1) & 15);
        System.out.println((0x61c88647 >> 2) & 15);
        System.out.println((0x61c88647 >> 3) & 15);



    }


    @Test
    public void testBase() {
        ThreadLocal t = new ThreadLocal();
    }
}
