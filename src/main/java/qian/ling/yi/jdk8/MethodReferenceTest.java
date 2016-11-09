package qian.ling.yi.jdk8;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import qian.ling.yi.AbstractTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by liuguobin on 2016/9/29.
 */
public class MethodReferenceTest extends AbstractTest{

    @Test
    public void testObjectRefer() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        Map<String, String> map2 = new HashMap<>();
        map2.put("2", "2");
        Map<String, String> map3 = new HashMap<>();
        map3.put("3", "3");
        Map<String, String> map4 = new HashMap<>();
        map4.put("4", "4");
        Map<String, String> map5 = new HashMap<>();
        map5.put("5", "5");
        List<Map<String, String>> list = new ArrayList<>();
        list.add(map);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        logger.info(JSON.toJSONString(list));
        logger.info(JSON.toJSONString(fetchData(list)));
    }
    private List<Map<String, String>> fetchData(List<Map<String, String>> datas) {
        return datas.stream()
                .map(this::fetchXMap)
                .map((data)->fetchXMap(data))// = data -> fetchXMap(data)
                .map(data -> {data.put("fetch","afterFetch");return data;})
                .collect(Collectors.toList());
    }

    private Map<String, String> fetchXMap(Map<String, String> data) {
        data.put("fetch", "afterFetch");
        return data;
    }


}
