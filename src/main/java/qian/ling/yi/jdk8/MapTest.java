package qian.ling.yi.jdk8;

import java.util.stream.Stream;

public class MapTest {
    @Test
    public void test() {
        long m = Stream.of(1).map(n -> {
            System.out.println("m");
            return null;
        }).count();
        System.out.println(m);
    }
}
