package qian.ling.yi.collection;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.io.InputStream;
import java.lang.reflect.Array;

/**
 * Created by liuguobin on 2016/11/1.
 */
public class ArrayTest extends AbstractTest {

    /**
     * 创建数组的两种方式
     */
    @Test
    public void testCreate() {

        int[] intArray = new int[10];
//      动态创建对象数组
        int[] tmp = (int[]) Array.newInstance(String.class, 10);

    }

}
