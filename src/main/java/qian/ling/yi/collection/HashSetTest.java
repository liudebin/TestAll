package qian.ling.yi.collection;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.HashSet;

/**
 * Created by liuguobin on 2016/11/10.
 */
public class HashSetTest extends AbstractTest{

    @Test
    public void testAdd() {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("1");
        logger.info("{}", hashSet.size());
        hashSet.add("2");
        logger.info("{}", hashSet.size());
        hashSet.add("1");
        logger.info("{}", hashSet.size());
    }

    @Test
    public void testContain() {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("1");
        logger.info("{}", hashSet.contains("1"));
    }
}
