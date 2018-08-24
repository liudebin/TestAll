package qian.ling.yi.thread.intro.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Semaphore2AcquireUninterruptibly
 *
 * @author liuguobin
 * @date 2018/5/14
 */

public class Semaphore2AcquireUninterruptibly {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
//        Service service = new Service() {
//            @Override
//            public void testMethod(Semaphore semaphore) {
//                try {
//                    semaphore.acquire();
//                    System.out.println(Thread.currentThread().getName()
//                            + " begin timer=" + System.currentTimeMillis());
//                    for (int i = 0; i < Integer.MAX_VALUE / 50; i++) {
//                        String newString = Thread.currentThread().getName() + String.valueOf(i);
////                        System.out.println(newString);
//                    }
//                    System.out.println(Thread.currentThread().getName()
//                            + "   end timer=" + System.currentTimeMillis());
//                    semaphore.release();
//                } catch (InterruptedException e) {
//                    System.out.println("线程" + Thread.currentThread().getName()
//                            + "进入了catch");
////                    System.exit(-1);
//                    e.printStackTrace();
//                }
//            }
//        };

        Service service = new Service() {
            @Override
            public void testMethod(Semaphore semaphore) {
                    semaphore.acquireUninterruptibly();
                    System.out.println(Thread.currentThread().getName()
                            + " begin timer=" + System.currentTimeMillis());
                    for (int i = 0; i < Integer.MAX_VALUE / 50; i++) {
                        String newString = Thread.currentThread().getName() + String.valueOf(i);
//        System.out.println(newString);
                    }
                    System.out.println(Thread.currentThread().getName()
                            + "   end timer=" + System.currentTimeMillis());
                System.out.println(semaphore.availablePermits());
                    semaphore.release();
                System.out.println(semaphore.availablePermits());
                System.out.println(Thread.currentThread().getName() + Thread.currentThread().isInterrupted());
            }
        };


        /**
         *
         A begin timer=1526284530792
         main中断了b
         A   end timer=1526284532104
         B begin timer=1526284532105
         B   end timer=1526284533363
         */

        Thread a = new Thread(()-> {service.testMethod(semaphore);});
        a.setName("A");
        a.start();

        Thread b = new Thread(()-> {service.testMethod(semaphore);});
        b.setName("B");
        b.start();

        Thread.sleep(1000);

        b.interrupt();
        System.out.println(b.isInterrupted());
        System.out.println("main中断了b");
    }
}
