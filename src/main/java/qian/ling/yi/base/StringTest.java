package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

/**
 * Created by liuguobin on 2016/10/12.
 */
public class StringTest extends AbstractTest{

    /**
     * 开始的位置，从0开始，不存在则为-1
     * 00000
     */
    @Test
    public void testIndexOf(){
        String str = "abcde";
        logger.info("{}", str.indexOf("abc"));
        logger.info("{}", str.indexOf("abcde"));
        logger.info("{}", str.indexOf("a"));
        logger.info("{}", str.indexOf("bcd"));
        logger.info("{}", str.indexOf("bcf"));
        logger.info("{},length: {}", str.indexOf("e"), str.length());
        logger.info("{}", str.indexOf("e") + 1 == str.length());
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
        String a = "ab0cd";
        //截取输入值到末尾
        logger.info(a.substring(a.indexOf("0") + 1));
        a = "abcd0";
        logger.info(a.substring(a.indexOf("0")));

        logger.info("{}", Integer.parseInt("218555198007307124".substring(16,17))%2);

        logger.info("{}", a.substring(0, 1));
        logger.info("{}", a.substring(1, 2));
    }

    /**
     * "|" 竖线居然需要转义。
     */
    @Test
    public void testSplit() {
        String s = "a|b|c";
        String [] ss = s.split("\\|");
//        String [] ss = s.split("|");居然会变成五个 a | b | c
//        s = "a,b,c";
//        ss = s.split(",");
        for (int i = 0;i < ss.length; i++) {
            logger.info(ss[i]);
        }
    }

    @Test
    public void test() {
        String s = "0000123"; //123
        s = "00000000";//0
        s = "10000000";//10000
        s = "";
        for (int i=0; i< s.length(); i ++) {
            if (s.charAt(i) != '0') {
                logger.info(s.substring(i));
                return ;
            }
        }
        logger.info("0");

    }


    @Test
    public void testTrim() {
        logger.info("1" + "".trim() + "1");
        logger.info("1" + " ".trim() + "1");
    }

    @Test
    public void testMatch() {
        String s = "1231d";
        s = "123";
        logger.info("{}", s.matches("\\d*"));
    }

    @Test
    public void testValueOf() {
        Integer i = null;
        logger.info(String.valueOf(i));
    }

}
