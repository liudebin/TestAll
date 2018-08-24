package qian.ling.yi.thread.intro.semaphore;


import java.util.concurrent.Semaphore;

/**
 *
 */
public class Semaphore1Simple {

    static Semaphore semaphore = new Semaphore(1, true);
    static Runnable runnable = () -> {
        try {
            System.out.println("come on");
            semaphore.acquire(1);
            System.out.println(Thread.currentThread().getName()
                    + " begin timer=" + System.currentTimeMillis());
            int sleepValue = ((int) (Math.random() * 10000));
            System.out.println(Thread.currentThread().getName() + " 停止了"
                    + (sleepValue / 1000) + "秒");
            Thread.sleep(sleepValue);
            System.out.println(Thread.currentThread().getName()
                    + "   end timer=" + System.currentTimeMillis());
//                semaphore.release(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };
//	public static void main(String[] args) {

//
//
//	    Runnable runnable = () -> {
//            try {
//                semaphore.acquire(1);
//                System.out.println(Thread.currentThread().getName()
//                        + " begin timer=" + System.currentTimeMillis());
//                int sleepValue = ((int) (Math.random() * 10000));
//                System.out.println(Thread.currentThread().getName() + " 停止了"
//                        + (sleepValue / 1000) + "秒");
//                Thread.sleep(sleepValue);
//                System.out.println(Thread.currentThread().getName()
//                        + "   end timer=" + System.currentTimeMillis());
////                semaphore.release(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        };
//        final Thread threadA = new Thread(runnable);
//        final Thread threadB = new Thread(runnable);
//        final Thread threadC = new Thread(runnable);
//        threadA.setName("A");
//        threadB.setName("B");
//        threadC.setName("C");
//        threadA.start();
//        threadB.start();
//        threadC.start();
//        semaphore.release(1);
//        semaphore.release(1);
//        semaphore.release(1);
//    }


//    public static void main(String[] args) throws InterruptedException {
//        Semaphore semaphore = new Semaphore(1, true);
//
//        Thread.currentThread().setName("main");
//
//        runnable.run();
//        final Thread threadA = new Thread(runnable);
//        threadA.setName("threadA");
//        threadA.start();
//        Thread.sleep(5000l);
//        semaphore.release();
//        try {
//            semaphore.acquire(1);
//            System.out.println(Thread.currentThread().getName()
//                    + " begin timer=" + System.currentTimeMillis());
//            int sleepValue = ((int) (Math.random() * 10000));
//            System.out.println(Thread.currentThread().getName() + " 停止了"
//                    + (sleepValue / 1000) + "秒");
//            Thread.sleep(sleepValue);
//            System.out.println(Thread.currentThread().getName()
//                    + "   end timer=" + System.currentTimeMillis());
////                semaphore.release(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }

    public static void main(String[] args) throws InterruptedException {
        runnable.run();
        new Thread(runnable).start();
        Thread.sleep(60000);

        System.out.println("release");
        semaphore.release();
    }



}
