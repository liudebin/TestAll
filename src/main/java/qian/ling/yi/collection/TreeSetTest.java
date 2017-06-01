package qian.ling.yi.collection;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * TreeSet测试
 *
 * @author liuguobin
 * @date 2017/4/25
 */

public class TreeSetTest extends AbstractTest {

    @Test
    public void test() {
        Set<String> userId = new TreeSet<>();
        userId.add("1");
        userId.add("2");
        List<String> list = new ArrayList<>(userId);
        list.forEach(s -> logger.info(s));
    }
}
