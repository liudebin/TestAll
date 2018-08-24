package qian.ling.yi.thread.sourceRead;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * UnSafeTest
 * UnSafe方法的使用说明
 *
 * @author liuguobin
 * @date 2018/5/10
 */

public class UnsafeTest {

    static Unsafe unsafe;
    volatile int state;
    static long stateOffset;
    static UnsafeTest unSafeTest;
    static {
        try {
            Constructor<Unsafe> constructor = Unsafe.class.getDeclaredConstructor(new Class<?>[0]);
            constructor.setAccessible(true);
            unsafe = constructor.newInstance(new Object[0]);
        } catch (NoSuchMethodException | InstantiationException
                | IllegalAccessException|InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            //获取实例字段的内存地址
            stateOffset = unsafe.objectFieldOffset
                    (UnsafeTest.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void s() throws NoSuchFieldException {
//    public static void main(String[] args) throws NoSuchFieldException {
        System.out.println(stateOffset);
        UnsafeTest unSafeTest = new UnsafeTest();
        unSafeTest.state = 2;
        System.out.println(unSafeTest.compareAndSwapInt(2,3));
        System.out.println(unSafeTest.state);
        System.out.println(unSafeTest.compareAndSwapInt(2,3));
        System.out.println(unSafeTest.state);


        int[] arr = {1,2,3,4,5,6,7,8,9,10};

        int b = unsafe.arrayBaseOffset(int[].class);

        int s = unsafe.arrayIndexScale(int[].class);

        unsafe.putInt(arr, (long)b+s*9, 1);

        for(int i=0;i<10;i++) {

            int v = unsafe.getInt(arr, (long) b + s * i);
//            Object v = unsafe.getObjectVolatile(arr, b+s*i);

            System.out.print(v + " ");
        }
        System.out.println();
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    boolean compareAndSwapInt(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }




    /**
     *
     *
     *
     *
     * 跟先后顺序有点关系。应该跟线程有关
     *
     *
     * 线程内部，可以先unpark， 后 park。
     * 多线程，线程内park，线程外UNpark。
     *
     * 错了，错了。
     * 在调用对目标线程的unpark的时候，目标线程必须是活的，如果是start前调用是不起作用的，因为线程未激活。
     *
     * 所以因为不知道这个导致，白写了那么多测试用例。
     * @param args
     * @throws InterruptedException
     */
    public static  void main(String[] args) throws InterruptedException {
        Thread currThread = Thread.currentThread();

//        unsafe.unpark(currThread); // 为给定线程提供执照。 不可叠加的，一次性的。用完即费？
// 没有使用过，也被费调？没有被废掉。
//        unsafe.unpark(currThread);
        Thread thread = new Thread((() -> {
            System.out.println("begin");
            unsafe.park(false, 0);
            System.out.println("SUC");
        }));
//        unsafe.unpark(thread);
//        unsafe.unpark("hehe");
        thread.start();
        System.out.println("------");
        unsafe.unpark(thread);
//        unsafe.park(false, 0);
        unsafe.park(false, 0);// 线程调用park函数等待执照。

        System.out.println("SUCCESS!!!");
        Thread.sleep(10000);

    }

    @Test
    public void testPark() throws InterruptedException {
        Thread thread = new Thread((() -> {
            System.out.println("begin");
            unsafe.park(false, 0);
            System.out.println("SUC");
        }));
        thread.start();
        System.out.println("------");
        Thread.sleep(5000);
//        unsafe.unpark("hehe");
        unsafe.unpark(thread);
        System.out.println("succcc");
        Thread.sleep(10000l);

    }

    @Test
    public void testPark2() throws InterruptedException {
        Thread thread = new Thread((() -> {
            unsafe.unpark(Thread.currentThread());
            System.out.println("begin");
            unsafe.park(false, 0);
            System.out.println("SUC");
        }));
        thread.start();
        System.out.println("------");
        Thread.sleep(5000);
//        unsafe.unpark("hehe");
//        unsafe.unpark(thread);
        System.out.println("succcc");
        Thread.sleep(10000l);

    }

    /**
     * 线程1的unPark ,必须在线程1 park之后才能被释放。
     * @throws InterruptedException
     */
    @Test
    public void testPark3() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            System.out.println("begin1");
            unsafe.park(false, 0);
            System.out.println("SUC1");
        });
        Thread thread2 = new Thread((() -> {
            System.out.println("begin2");
            unsafe.unpark(thread1);
            System.out.println("SUC2");
        }));
        System.out.println("------");
        thread2.start();
        Thread.sleep(5000);
        thread1.start();
//        unsafe.unpark("hehe");
//        unsafe.unpark(thread);
        System.out.println("succcc");
        Thread.sleep(10000l);

    }


    @Test
    public void testParkTime() {
        long na = System.nanoTime();
        System.out.println(1);
        unsafe.park(false, 1000000000);
        System.out.println("停了多久呢？" + (System.nanoTime() - na));
    }
    @Test
    public void Test() {
        Thread currThread = Thread.currentThread();
        new Thread(()->{
            try {
                Thread.sleep(3000);
                currThread.interrupt();
                //unsafe.unpark(currThread);
            } catch (Exception e) {}
        }).start();

        unsafe.park(false, 0);

        System.out.println("SUCCESS!!!");
    }

    //0 代表一直等
    @Test
    public void parkTime() {
        //相对时间后面的参数单位是纳秒
        long i;
        System.out.println((i = System.nanoTime()));

//        unsafe.park(false, 3000000000l);
//        unsafe.park(false, 1000000000l);
        unsafe.park(false, 0); // 0 代表一直等
        System.out.println("SUCCESS!!!");
        System.out.println(System.nanoTime() - i);
    }

    @Test
    public void testTime2() {

        long time = System.currentTimeMillis()+3000;

        //绝对时间后面的参数单位是毫秒
        unsafe.park(true, time);

        System.out.println("SUCCESS!!!");
    }
}
