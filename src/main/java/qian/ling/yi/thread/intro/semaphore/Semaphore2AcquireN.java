package qian.ling.yi.thread.intro.semaphore;

import java.util.concurrent.Semaphore;

public class Semaphore2AcquireN {
	public static void main(String[] args) throws InterruptedException {
		Semaphore semaphore = new Semaphore(2);
		Service service = new Service() {
            @Override
            public void testMethod(Semaphore semaphore) {
                try {
                    System.out.println("acquire 3 begin " + Thread.currentThread().getName());
                    semaphore.acquire(3);
                    System.out.println("acquire 3 end " + Thread.currentThread().getName());
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + semaphore.availablePermits());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
		};
		Thread a = new Thread(() -> service.testMethod(semaphore));
		a.setName("A");
		Thread b = new Thread(() -> service.testMethod(semaphore));
		b.setName("B");
		Thread c = new Thread(() -> service.testMethod(semaphore));
		c.setName("C");
		a.start();
		b.start();
		c.start();

		Thread.sleep(6000);

		semaphore.release(5);
		Thread.sleep(8000);
        System.out.println(semaphore.availablePermits());
    }

}
