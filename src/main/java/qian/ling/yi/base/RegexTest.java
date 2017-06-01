package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式测试
 *
 * @author liuguobin
 * @date 2017/4/22
 */

public class RegexTest extends AbstractTest {

    @Test
    public void test() {
        String s = "3004-TRQT-000113-000123-20170421";
        String rex = "\\d{4}-TRQT-\\d{6}-\\w{6}-\\d{8}";
        Pattern p1 = Pattern.compile(rex);
        Matcher m1 = p1.matcher(s);
        boolean match = m1.matches();
        logger.info("{}", match);
    }
}
