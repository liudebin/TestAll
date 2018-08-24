package qian.ling.yi.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * LinkedHashMapTest
 *
 * @author liuguobin
 * @date 2018/6/13
 */

public class LinkedHashMapTest {

    @Test
    public void test() {
        LinkedHashMap map = new LinkedHashMap(64);
        map.put("12", 1);
    }

    @Test
    public void testPut() {
        KeyTest a = new KeyTest();
        KeyTest b = new KeyTest();
        KeyTest c = new KeyTest();
        KeyTest d = new KeyTest();
        KeyTest e = new KeyTest();
        KeyTest f = new KeyTest();
        KeyTest g = new KeyTest();
        KeyTest h = new KeyTest();
        KeyTest i = new KeyTest();
        KeyTest j = new KeyTest();
        KeyTest k = new KeyTest();

        LinkedHashMap map = new LinkedHashMap(64);
        map.put(a, 33);
        map.put(b, 22);
        map.put(c, 100);
        map.put(d, 29);
        map.put(e, 90);
        map.put(f, 24);
        map.put(g, 87);
        map.put(h, 64);
        map.put(i, 55);

        map.put("1", 2);
        map.put("2", 3);
        map.put("3", 4);
        for (Object o : map.values()) {
            System.out.println(o);
        }



        HashMap map1 = new HashMap(64);
        map1.put(a, 33);
        map1.put(b, 22);
        map1.put(c, 100);
        map1.put(d, 29);
        map1.put(e, 90);
        map1.put(f, 24);
        map1.put(g, 87);
        map1.put(h, 64);
        map1.put(i, 55);
        map1.put("1", 2);
        map1.put("2", 3);
        map1.put("3", 4);
        for (Object o : map1.values()) {
            System.out.println(o);
        }



    }
}
