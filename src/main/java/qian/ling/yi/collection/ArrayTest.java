package qian.ling.yi.collection;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liuguobin on 2016/11/1.
 */
public class ArrayTest extends AbstractTest {

    /**
     * 实现说明
     */

    /**
     * 创建数组的两种方式
     */
    @Test
    public void testCreate() {

        int[] intArray = new int[10];
//      动态创建对象数组
        int[] tmp = (int[]) Array.newInstance(String.class, 10);

    }

    @Test
    public void testAsList() {
        String[] s = {"a", "b"};
        List<String> list = Arrays.asList(s); //返回的结果是一个只读列表
        logger.info(JSON.toJSONString(list));
        s[0] = "c";
        logger.info(JSON.toJSONString(list));

//        list.add("d");  // 报异常
    }

}
