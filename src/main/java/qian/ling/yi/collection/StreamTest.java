package qian.ling.yi.collection;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.stream.Stream;

/**
 * Created by liuguobin on 2016/11/16.
 */
public class StreamTest extends AbstractTest{

    @Test
    public void test() {
        Stream<String> stream = getArray();
        stream.forEach(str -> logger.info(str));
    }

    public Stream<String> getArray() {
//        return Stream.of("heh", "eh");
        return Stream.of(null);
    }
}
