package qian.ling.yi.jdk8;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.Optional;

/**
 * Optional的相关测试
 *
 * @author liuguobin
 * @date 2017/5/25
 */

public class OptionalTest extends AbstractTest{
    @Test
    public void test() {
        Optional<String> op = Optional.of("123");
    }
}
