package qian.ling.yi.collection;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ArrayList的测试用例
 *
 * @author liuguobin
 * @date 2017/4/17
 */

public class ArrayListTest extends AbstractTest{

    @Test
    public void testInitialCapacity() {
        List<String> list = new ArrayList<>(2);
        logger.info("{}", list.size());
        list.add("1");
        list.add("2");
        list.add("3");
        logger.info(list.get(2));
    }

    @Test
    public void testShuffle() {
        List<String> list = new ArrayList<>(2);
        list.add("1");
        list.add("2");
        list.add("3");
        logger.info(list.toString());

        //打乱
        Collections.shuffle(list);

        logger.info(list.toString());
    }


}
