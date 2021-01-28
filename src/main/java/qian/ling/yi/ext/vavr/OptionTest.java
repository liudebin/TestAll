package qian.ling.yi.ext.vavr;

import io.vavr.control.Option;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class OptionTest {
    @Test
    public void test() {
        Optional<String> maybeFoo = Optional.of("foo"); //(1)
        Optional<String> maybeFooBar = maybeFoo.map(s -> (String)null)  //(2)
                .map(s -> s.toUpperCase() + "bar");
        System.out.println(maybeFooBar.isPresent());
    }

    /**
     * some 并不会转为 none
     */
    @Test
    public void testOf() {
        Option<String> maybeFoo = Option.of("foo");
        try {
            Option<String> map = maybeFoo.map(s -> (String) null);
            System.out.println(map.isDefined());
            System.out.println(map.isEmpty());
            map
                    .map(s -> s.toUpperCase() + "bar");
            Assert.fail();
        } catch (NullPointerException e) {
            // this is clearly not the correct approach
            e.printStackTrace();
        }
    }
}
