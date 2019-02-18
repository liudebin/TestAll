package qian.ling.yi.collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @date: 2019/2/18.
 * @author: guobin.liu@holaverse.com
 */

public class HashMapTest {
    Logger logger = LoggerFactory.getLogger(HashMapTest.class);

    @Test
    public void testResize(){
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " : " + tableSizeFor(i));
        }
    }

    @Test
    public void testSize(){
        HashMap<String, String> t = new HashMap<>();
        t.put("1", "1");
//        之后t.table 就已经变成16了，且还resize了。

        //到1即resize
        //到4即resize
        HashMap<String, String> t1 = new HashMap<>(4);
        t1.put("1", "1");
        t1.put("2", "1");
        t1.put("3", "1");
        t1.put("4", "1");

        //到12 resize
        HashMap<String, String> t2 = new HashMap<>(16);
        t1.put("1", "1");
        t1.put("2", "1");
        t1.put("3", "1");
        t1.put("4", "1");
        t1.put("5", "1");
        t1.put("6", "1");
        t1.put("7", "1");
        t1.put("8", "1");
        t1.put("9", "1");
        t1.put("10", "1");
        t1.put("11", "1");
        t1.put("12", "1");

    }


    class HashMapT extends HashMap {
        static final int MAXIMUM_CAPACITY = 1 << 30;

    }

    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= HashMapT.MAXIMUM_CAPACITY) ? HashMapT.MAXIMUM_CAPACITY : n + 1;
    }


    class HashCodeKey{

        @Override
        public  int hashCode() {
            return 1;
        }

    }


}
