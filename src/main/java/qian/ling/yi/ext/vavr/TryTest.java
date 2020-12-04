package qian.ling.yi.ext.vavr;

import io.vavr.collection.List;
import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

public class TryTest {
    @Test
    public void testBase() {
        Try<Integer> of = Try.of(() -> 1 / 0);
        System.out.println(of.isFailure());
        System.out.println(of.getCause());
        System.out.println(of.failed().get());
        System.out.println(of.get());

        List.of(1,2,3);
    }
}
