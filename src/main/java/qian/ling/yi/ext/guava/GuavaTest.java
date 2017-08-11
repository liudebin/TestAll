package qian.ling.yi.ext.guava;

import com.google.common.cache.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import qian.ling.yi.AbstractTest;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * guavaTest
 *
 * @author liuguobin
 * @date 2017/8/7
 */

public class GuavaTest extends AbstractTest {

    @Test
    public void testGetCallable() throws Exception{
        Cache<String, Integer> cache = CacheBuilder.newBuilder().build();
        Long s = System.nanoTime();
        Integer value = cache.get("key", ()-> {
            Thread.sleep(10000);
            return 100;
        });
        System.out.println(value);
        System.out.println(System.nanoTime() - s);
    }

    @Test
    public void testGet() throws Exception{
        LoadingCache<String, Integer> graphs = CacheBuilder.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .removalListener(new MyListener())
                .build(new CacheLoader<String, Integer>() {
                    public Integer load(String key) {
                        System.out.println("load");
                        return 1;
                    }
                });

        Integer s = graphs.get("12");
        logger.info("{}", s);
        s = graphs.get("12");
        logger.info("{}", s);
        Thread.sleep(5000);
        logger.info("{}", s);
        System.out.println(graphs.get("12"));
        logger.info("{}", s);

        graphs.refresh("key");
        graphs.refresh("key");
    }

    @Test
    public void testCache() throws Exception{
        LoadingCache<String, Integer> graphs = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(100, TimeUnit.SECONDS)
                .removalListener(new MyListener())
                .build(new CacheLoader<String, Integer>() {
                    public Integer load(String key) {
                        return 1;
                    }
                });

        logger.info(graphs.asMap().toString());
        Integer s = graphs.get("12");
        System.out.println(s);
        logger.info(graphs.asMap().toString());

        Thread.sleep(15000);
        logger.info(graphs.asMap().toString());
//        System.out.println(graphs.get("12"));
        logger.info("{}", graphs.get("12"));
        logger.info(graphs.asMap().toString());
        Thread.sleep(30000);
        logger.info(graphs.asMap().toString());

    }


    @Test
    public void testOverSize() throws Exception{
        LoadingCache<String, Integer> graphs = CacheBuilder.newBuilder()
                .maximumSize(10000) // 在缓存项达到限定值执之前，缓存就可能进行回收操作- 缓存项的数目逼近限定值时。
//                .expireAfterWrite(100, TimeUnit.SECONDS)
                .removalListener(new MyListener())
                .build(new CacheLoader<String, Integer>() {
                    public Integer load(String key) {
                        return 1;
                    }
                });

        for (int i = 0 ; i < 1000; i ++) {
            graphs.get(i + "");
        }
        Set<String> set = graphs.asMap().keySet();
        System.out.println(set.stream().sorted());
        graphs.get("1001");
        Set<String> set1 = graphs.asMap().keySet();
        System.out.println(set1.stream().sorted());
//        System.out.println(graphs.asMap());
        graphs.get("10002");
        Set<String> set2 = graphs.asMap().keySet();
//        System.out.println(graphs.asMap());
    }

    @Test
    public void testRecord() throws ExecutionException {
        LoadingCache<String, Integer> graphs = CacheBuilder.newBuilder()
                .maximumSize(1000) // 在缓存项达到限定值执之前，缓存就可能进行回收操作- 缓存项的数目逼近限定值时。
//                .expireAfterWrite(100, TimeUnit.SECONDS)
                .removalListener(new MyListener())
                .recordStats()
                .build(new CacheLoader<String, Integer>() {
                    public Integer load(String key) {
                        return 1;
                    }
                });

        for (int i = 0 ; i < 1000; i ++) {
            graphs.get(i + "");
        }
        for (int i = 0 ; i < 1000; i ++) {
            graphs.get(i + "");
        }
        graphs.get("1001");
        graphs.get("10002");
        System.out.println(graphs.stats());
//        CacheStats{hitCount=1000, missCount=1002, loadSuccessCount=1002, loadExceptionCount=0, totalLoadTime=9253629, evictionCount=0}
        /**
         * hitRate()：缓存命中率；
         * averageLoadPenalty()：加载新值的平均时间，单位为纳秒；
         * evictionCount()：缓存项被回收的总数，不包括显式清除。
         */
    }


    class MyListener implements RemovalListener<String, Integer> {
        Logger logger = LoggerFactory.getLogger(MyListener.class);
        /**
         * Notifies the listener that a removal occurred at some point in the past.
         * <p>
         * <p>This does not always signify that the key is now absent from the cache,
         * as it may have already been re-added.
         *
         * @param notification
         */
        @Override
        public void onRemoval(RemovalNotification notification) {
            logger.info("remove {}", notification.getKey());
        }
    }
}
