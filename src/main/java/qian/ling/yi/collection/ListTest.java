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


    /**
     * 在不知道class类型的情况下，可以使用Array.newInstance
     */
    @Test
    public void testArrayNew() {
        Object tmp = Array.newInstance(String.class, 10);
        Object[] task = {};
        String[] array =  {"1", "2", };
        for (int i = 0; i < array.length; i ++) {
            String s = array[i];
            logger.info("{}", s);
        }
    }

    /**
     * 不能为空，值为0
     */
    @Test
    public void testEmpty() {
        List list = new ArrayList();
        list = null;
        logger.info("{}", list.isEmpty());
    }

    /**
     * contain方法，可以直接判断是否包含为空变量
     */
    @Test
    public void testContain() {
        List<String> s = new ArrayList<>();
        s.add("he");
        logger.info("{}", s.contains("he"));
        logger.info("{}", s.contains("s"));
        logger.info("{}", s.contains(null));
    }
}
