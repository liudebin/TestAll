package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuguobin on 2016/12/26.
 */
public class StringBuilderTest extends AbstractTest {


    /**
     * 对象引用
     */
    @Test
    public void testReference() {
        StringBuilder sb = new StringBuilder("1");
        StringBuilder db = new StringBuilder("2");
        List<StringBuilder> list = new ArrayList<>(2);
        list.add(sb);
        list.add(db);
        test(list);
        for (int i = 0; i <list.size(); i ++) {
            logger.info(list.get(i).toString());
        }
    }

    public void test(List<StringBuilder> test) {
        for (int i = 0; i <test.size(); i ++) {
            StringBuilder sb = test.get(i);
            sb.append("i");
        }
    }

    @Test
    public void testAppend() {
        StringBuilder s = new StringBuilder("a");
        s.append("s");
    }
}
