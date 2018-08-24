package qian.ling.yi.thread.intro.phaser;

import org.junit.Test;

import java.util.concurrent.Phaser;

/**
 * arriveAndAwaitAdvance
 *
 * @author liuguobin
 * @date 2018/5/17
 */

public class PhaserTest {
    static  Phaser phaser = new Phaser(2);

    @Test
    public void testArriveAndAwaitAdvance() {
        Thread a = new Thread(PhaserTest::methodA);
        Thread b = new Thread(PhaserTest::methodB);
        a.setName("A");
        a.start();
        b.setName("B");
        b.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testArriveAndDeregiester() {
        Thread a = new Thread(PhaserTest::methodA);
        Thread b = new Thread(PhaserTest::methodDeregister);
        a.setName("A");
        a.start();
        b.setName("C");
        b.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetPhaseAndOnAdvance() {
        Phaser phase = new Phaser(2) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("onAdvance");
                boolean s = super.onAdvance(phase, registeredParties);
                System.out.println(s);
                return s;
            }
        };

        Thread a = new Thread(() -> {
            System.out.println("begin");
            phase.arriveAndAwaitAdvance();
            System.out.println("end");
        });
        a.run();

    }


    @Test
    public void register() {
        System.out.println(phaser.getRegisteredParties());
        phaser.register();
        System.out.println(phaser.getRegisteredParties());
        phaser.register();
        System.out.println(phaser.getRegisteredParties());
        phaser.register();
        System.out.println(phaser.getRegisteredParties());
        phaser.register(); //动态添加一个parties值
        System.out.println(phaser.getRegisteredParties());
        phaser.arrive(); //并不会增加
        System.out.println(phaser.getRegisteredParties());

        phaser.bulkRegister(3); //并不会增加
        System.out.println(phaser.getRegisteredParties());
    }

    @Test
    public void Arrrived() {

        System.out.println(phaser.getRegisteredParties());
        new Thread(() -> {phaser.arriveAndAwaitAdvance();
            System.out.println(phaser.getArrivedParties());
            System.out.println(phaser.getUnarrivedParties());}).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main" + phaser.getArrivedParties());
        System.out.println("main" + phaser.getUnarrivedParties());
    }



    public static  void methodA() {
        System.out.println(Thread.currentThread().getName() + " A1 begin="
                + System.currentTimeMillis());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + " A1   end="
                + System.currentTimeMillis());

        System.out.println(Thread.currentThread().getName() + " A2 begin="
                + System.currentTimeMillis());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + " A2   end="
                + System.currentTimeMillis());
    }

    public static void methodB() {
        try {
            System.out.println(Thread.currentThread().getName() + " B1 begin="
                    + System.currentTimeMillis());
            Thread.sleep(5000);
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + " B1   end="
                    + System.currentTimeMillis());

            System.out.println(Thread.currentThread().getName() + " B2 begin="
                    + System.currentTimeMillis());
            System.out.println("phase: "+ phaser.getPhase());
            Thread.sleep(5000);
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + " B2   end="
                    + System.currentTimeMillis());
            System.out.println("phase: "+ phaser.getPhase());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static  void methodDeregister() {
        try {
            System.out.println(Thread.currentThread().getName() + " C1 begin="
                    + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("before arriveAndDeregister：" + phaser.getRegisteredParties());
            phaser.arriveAndDeregister();
            System.out.println("after arriveAndDeregister：" + phaser.getRegisteredParties());
            System.out.println(Thread.currentThread().getName() + " C1   end="
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
