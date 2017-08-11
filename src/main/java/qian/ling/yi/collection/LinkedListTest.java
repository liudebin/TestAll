package qian.ling.yi.collection;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 链表测试
 *
 * @author liuguobin
 * @date 2017/6/24
 */

public class LinkedListTest extends AbstractTest{

    @Test
    public void testRemove () {
        LinkedList<String> linkedList = new LinkedList();
        linkedList.add("1");
        linkedList.add("2");
        Iterator<String> it = linkedList.iterator();

        while (it.hasNext()) {
            String s = it.next();
            if (s.equals("1")) {
                it.remove();
                logger.info("{}", linkedList);
            }
        }
        logger.info("{}", linkedList);
    }
}
