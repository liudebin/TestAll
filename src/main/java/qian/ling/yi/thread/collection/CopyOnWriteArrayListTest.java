package qian.ling.yi.thread.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import qian.ling.yi.AbstractTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *CopyOnWrite容器
 *      即写时复制的容器。
 *      通俗的理解是当我们往一个容器添加元素的时候，内部实现的时候不直接往当前容器添加，而是先将当前容器进行Copy，
 *      复制出一个新的容器，然后新的容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器。
 *      这样做的好处是我们可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。
 *      所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
 *
 * 使用注意：
 *  1，使用批量添加，因为每次添加都要复制新的对象。
 * 缺点：
 *  1，内存占用：在添加元素的时候，会存在两个对象，如果对象占用的内存比较大，就会造成频繁的GC
 *          之前我们系统中使用了一个服务由于每晚使用CopyOnWrite机制更新大对象，造成了每晚15秒的Full GC，应用响应时间也随之变长。
 *          针对内存占用问题，可以通过压缩容器中的元素的方法来减少大对象的内存消耗，比如，如果元素全是10进制的数字，可以考虑把它压缩成36进制或64进制。
 *          或者不使用CopyOnWrite容器，而使用其他的并发容器，
 *  2，数据一致性问题。
 *      CopyOnWrite容器只能保证数据的最终一致性，不能保证数据的实时一致性。
 *      如果你希望写入的的数据，马上能读到，请不要使用CopyOnWrite容器。
 * Created by liuguobin on 2016/11/1.
 */
public class CopyOnWriteArrayListTest extends AbstractTest {
    public static Logger logger = LoggerFactory.getLogger(CopyOnWriteArrayListTest.class);


    /**
     * 要表现的就是，在集合添加元素的时候，并不能即时反应加入结果。so 是读写分离
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
//        CopyOnWriteArrayList<Map<String, String>> test= new CopyOnWriteArrayList<Map<String, String>>();
        CopyOnWriteArrayList<String> test= new CopyOnWriteArrayList<>();
        test.add("1");
        Thread thread = new Thread(() -> {
            test.forEach(str -> logger.info(str));
            while (true) {
                logger.info("size {}", test.size());
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        while (true) {
            List<String> list = new ArrayList<>(10000000);
            for(int i = 0;i < 10000000;i ++) {
                list.add("a");
            }
            thread.start();
            logger.info("开始添加了");
            test.addAll(list);
            logger.info("添加之后了，在休眠ing");
            Thread.sleep(3000);
        }

    }
}
