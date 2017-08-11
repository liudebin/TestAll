package qian.ling.yi.thread.collection;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ConcurrentMap 是否有超时设置
 *
 * @author liuguobin
 * @date 2017/8/7
 */

public class ConcurrentMapTest extends AbstractTest {

    @Test
    public void testPut() throws InterruptedException, ExecutionException {
        final AtomicInteger count = new AtomicInteger(10);
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10, r -> new Thread(r, "test-"+ count.getAndIncrement()));
        // 最终只有一个值，能说明线程安全吗
        map.put("key", 0);
        List<Future> list = new LinkedList<>();
        for ( int i = 0 ;i < 100000 ;  i ++) {
//            System.out.println("i.get = " + i.get());
             list.add(executorService.submit(() -> {
//                map.computeIfAbsent("key", k -> map.get("key") + 1);
                map.compute("key", (k,v) -> v + 1);
//                System.out.println("map.get(\"key\") = " + map.get("key"));
            }));
        }
        map.put("key", 100000);
        for (Future future : list) {
            future.get();
        }

        System.out.println(map.get("key"));
    }

    @Test
    public void testCompute() {
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
//        concurrentHashMap.put("key", 2);
        //原子性操作 key 和 对应value， 无论存不存在
        concurrentHashMap.compute("key", (key, value) -> {
            System.out.println(key);
            System.out.println(value);
            return value  + 1;
        });
        System.out.println(concurrentHashMap.get("key"));
    }

    @Test
    public void testComputeIfPresent() {
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        //key 存在的情况下，原子性操作 key 和 对应value

        System.out.println(concurrentHashMap.computeIfPresent("key", (key, value) -> {
            System.out.println(key);
            System.out.println(value);
            return value  + 1;
        }));
    }

    @Test
    public void testComputeIfAbsent() {
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        //key不存在的情况下，原子性操作 key
        System.out.println(concurrentHashMap.computeIfAbsent("key", (key) -> {
            System.out.println(key);
            return 1  + 1;
        }));
    }


}
