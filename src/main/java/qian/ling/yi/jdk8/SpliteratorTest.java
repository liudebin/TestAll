package qian.ling.yi.jdk8;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Spliterator java8 中的stream 中有用到
 *
 * @author liuguobin
 * @date 2017/12/13
 */

public class SpliteratorTest extends AbstractTest {
    @Test
    public void tt() {
        String arr = "12%3 21sdas s34d dfsdz45   R3 jo34 sjkf8 3$1P 213ikflsd fdg55 kfd";
        Stream<Character> stream = IntStream.range(0, arr.length()).mapToObj(arr::charAt);
        // 调用parallel()变成并行流
//        System.out.println("ordered total: " + stream.parallel().count());
        final Object[] objects = stream.toArray();
        final List<Object> list = Arrays.asList(objects);
//        list.spliterator().
        logger.info("{}", JSON.toJSON(objects));


    }
}
