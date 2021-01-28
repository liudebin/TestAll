package qian.ling.yi.ext.vavr;

import io.vavr.control.Option;
import org.junit.Test;

import static io.vavr.API.*;
import static io.vavr.Predicates.*;

public class PatternTest {
    @Test
    public void testBase() {
        Option<String> s = Match(1).option(
                Case($(0), "zero"),
                Case($(1), "one")
        );
        System.out.println(s.isDefined());
        System.out.println(s.get());
    }

    @Test
    public void testPredicate() {
        String s = Match(3).of(
                Case($(is(1)), "one"),
                Case($(is(2)), "two"),
                Case($(), "?")
        );
        System.out.println(s);
    }
}
