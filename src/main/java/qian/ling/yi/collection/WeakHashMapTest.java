package qian.ling.yi.collection;

import org.junit.Test;

import java.util.WeakHashMap;

/**
 * WeakHashMapTest
 *
 * @author liuguobin
 * @date 2018/6/13
 */

public class WeakHashMapTest {

    /**
     * 与预期一致，gc后就会被回收
     * 1
     * 0
     * @throws InterruptedException
     */
    @Test
    public void testPut() throws InterruptedException {
        WeakHashMap map = new WeakHashMap(16);
        Object k = new Object();
        map.put(k, 1);
        System.out.println(map.size());
        k = null;
        System.gc();// 建议JVM进行GC，只是建议。
        Thread.sleep(10000);
        System.out.println(map.size());
    }

    @Test
    public void testPutNullList() throws InterruptedException {
        WeakHashMap map = new WeakHashMap(128);
        Object k = new KeyTest();
        Object k1 = new KeyTest();
        String a = "11";
        map.put(k, a);
        map.put(k1, a);
        System.out.println(map.size());
        k1 = null;
        System.gc();// 建议JVM进行GC，只是建议。
        Thread.sleep(10000);
        System.out.println(map.size());

    }

    @Test
    public void testPutNull() {
        WeakHashMap map = new WeakHashMap();
        map.put(null,null);
    }






}
