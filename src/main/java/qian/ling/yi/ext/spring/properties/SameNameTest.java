package qian.ling.yi.ext.spring.properties;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import qian.ling.yi.ext.spring.bootstrap.AbstractContextLoaderTest;

/**
 * TODO
 *
 * @date: 2018/10/9.
 * @author: guobin.liu@holaverse.com
 */

public class SameNameTest extends AbstractContextLoaderTest {

    @Value("${a}")
    String a;
    @Value("${b}")
    String b;

    @Value("${c}")
    String c;
    @Test
    public void test() {
        System.out.println(a + b + c);
    }
}
