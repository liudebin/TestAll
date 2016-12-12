package qian.ling.yi.jdk8;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 测试List的foreach
 * Created by liuguobin on 16/10/18.
 */
public class ListForeach extends AbstractTest {
    List<Map<String, String>> list = new LinkedList<>();

    @Before
    public void before() {
        Map<String, String> tmp = new HashMap<>();
        tmp.put("1", "2");
        list.add(tmp);
        tmp.put("1", "3");
        list.add(tmp);
        tmp.put("1", "4");
        list.add(tmp);
        tmp.put("1", "5");
        list.add(tmp);
        tmp.put("1", "6");
        list.add(tmp);
        tmp.put("1", "7");
        list.add(tmp);
        tmp.put("1", "8");
        list.add(tmp);
        tmp.put("1", "9");
        list.add(tmp);
        tmp.put("1", "0");
        list.add(tmp);
    }

    @Test
    public void testTwoForeach() {
        list.forEach(tmp -> logger.info(tmp.toString()));
    }

    /**
     * foreach循环中，只要使用set，会直接修改
     */
    @Test
    public void testSet() {
        list.forEach(tmp -> tmp.put("1", "set"));
        list.forEach(tmp -> logger.info(tmp.toString()));
    }


    @Test
    public void testRemoveIf() {
        list.removeIf(map->map.containsKey("3"));
        list.forEach(tmp-> logger.info("{}", JSON.toJSONString(tmp)));
    }

}
