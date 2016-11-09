package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

/**
 * Created by liuguobin on 2016/10/12.
 */
public class StringTest extends AbstractTest{

    /**
     * 开始的位置，从0开始，不存在则为-1
     */
    @Test
    public void testIndexOf(){
        String str = "abcde";
        logger.info("{}", str.indexOf("abc"));
        logger.info("{}", str.indexOf("abcde"));
        logger.info("{}", str.indexOf("a"));
        logger.info("{}", str.indexOf("bcd"));
        logger.info("{}", str.indexOf("bcf"));
    }

    /**
     * 不能为null
     * “”返回length
     */
    @Test
    public void testLastIndexOf() {
        String str = "ababcd!@#";
        logger.info("{}", str.lastIndexOf("a"));
        logger.info("{}", str.lastIndexOf("ab"));
        logger.info("{}", str.lastIndexOf("abc"));
        logger.info("{}", str.lastIndexOf(""));
        logger.info("{}", str.lastIndexOf("e"));
        logger.info("{}", str.lastIndexOf(null));
    }

    /**
     * compareTo 不能跟null比较
     * 相等 0
     * 小于 -1
     * 大于  >0
     */
    @Test
    public void testCompareTo() {
        String a = "abcd";
        String b = "abc";
        String c = "a";
        String d = "ab";
        String e = "abcd";
        logger.info("{}", a.compareTo(a));
        logger.info("{}", a.compareTo(b));
        logger.info("{}", a.compareTo(c));
        logger.info("{}", a.compareTo(d));
        logger.info("{}", a.compareTo(e));
    }


    /**
     *
     */
    @Test
    public void testSubString() {
        String a = "abcd";
        //截取输入值到末尾
        logger.info(a.substring(a.lastIndexOf("a") + 1));
    }

}
