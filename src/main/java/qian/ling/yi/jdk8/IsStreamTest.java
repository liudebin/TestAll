package qian.ling.yi.jdk8;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

/**
 * TODO
 *
 * @date: 2018/11/13.
 * @author: guobin.liu@holaverse.com
 */

public class IsStreamTest extends AbstractTest {

    @Test
    public void isStreamMethod(){
//        Stream<String> a = Stream.of("a").filter();
//        Stream.of("a").flatMap()




//        Stream.of("a").max
//        Stream.of("a").redu
    }

    @Test
    public void maxByInt(){
        Optional<String> collect = Stream.of("2", "1", "3").collect(Collectors.maxBy(Comparator.comparing(n -> Integer.valueOf(n))));
        System.out.println(collect.get());
    }

    @Test
    public void parallelDiceRolls(){
        int N = 100;
        double fraction = 1.0 / N;
        Map<Integer, Double> collect = IntStream.range(0, N)
                .parallel()
                .mapToObj(twoDiceThrows())
                .collect(groupingBy(side -> side, summingDouble(n -> fraction)));
        System.out.println(JSON.toJSONString(collect));
    }

    private static IntFunction<Integer> twoDiceThrows() {
        return i -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int firstThrow = random.nextInt(1, 7);
            int secondThrow = random.nextInt(1, 7);
            return firstThrow + secondThrow;
        };
    }

    @Test
    public void testParallel(){
        long l = System.nanoTime();
        IntStream.range(1, 100).forEach(IsStreamTest::s);
        long l1 = System.nanoTime();
        System.out.println(l1 - l);
        IntStream.range(1, 100).parallel().forEach(IsStreamTest::s);
        System.out.println(System.nanoTime() - l1);
    }

    private static void s(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
