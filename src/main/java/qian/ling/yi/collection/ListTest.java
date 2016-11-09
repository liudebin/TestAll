package qian.ling.yi.collection;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by liuguobin on 2016/9/26.
 */
public class ListTest extends AbstractTest {

    @Test
    public void testForAddMap() {
        List<Map<String, String>> list = new LinkedList<>();
        Map<String, String> map = null;
        for (int i = 0; i < 100; i ++) {
            Map<String, String> tmp = new HashMap();
            tmp.put(i+"", i+"");
            list.add(tmp);
        }
        List<Map<String, String>> list1 = new LinkedList<>();
        for (int i = 0;i<100;i++) {
            map = list.get(i);
            list1.add(map);
        }
        for (int i = 0;i < 100; i++) {
            Map<String, String> map1 = list.get(i);
            logger.info(JSON.toJSONString(map1));
        }
    }


    @Test
    public void test() {
        Array.newInstance(String.class, 10);
        Object[] task = {};
    }

    @Test
    public void testEmpty() {
        List list = new ArrayList();
        logger.info("{}", list.isEmpty());
    }
}
