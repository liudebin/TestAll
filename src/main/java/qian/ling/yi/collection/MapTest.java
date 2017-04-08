package qian.ling.yi.collection;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.*;

/**
 * Created by liuguobin on 2016/9/27.
 */
public class MapTest extends AbstractTest{

    @Test
    public void sort(){
        Map<String, String> map = new HashMap<>();
        map.put("a","a");
        map.put("b","b");
        map.put("ab","a");
        map.put("c","c");
        map.put("d","d");
        logger.info(JSON.toJSONString(map));
        map.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEachOrdered(entry ->logger.info(entry.getKey()+":"+entry.getValue()));
    }

    @Test
    public void testStream() {
        Map<String, String> param = null;
        param =
                new HashMap<>();
        logger.info("{}", param.isEmpty());

        param.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(entry -> logger.info(entry.getKey()));
    }

    /**
     *  可以移除不存在的key
     */
    @Test
    public void testRemove() {
        Map<String, String> map = new HashMap<>();
        map.remove("haha");
    }

    @Test
    public void testInit() {
        Map<String, String> tmp = new HashMap<>(2);
        tmp.put("1", "1");
        tmp.put("2", "2");
        tmp.put("3", "3");
        tmp.keySet().forEach(key->logger.info(tmp.get(key)));
    }


    /**
     * 引用改变，会跟着改变
     */
    @Test
    public void testPut() {
        Map<String, List<String>> tmp = new HashMap<>();
        List<String> list = new LinkedList<>();
        tmp.put("tmp", list);
        logger.info(JSON.toJSONString(tmp));
        list.add("1");
        logger.info(JSON.toJSONString(tmp));
    }

    @Test
    public void convertTest() {
        List<Map> list = new ArrayList<>(1);
        Map map = null;
        list.add(map);
        Map map1 = list.get(0);
        Map map2 = (HashMap) map1;

    }
}
