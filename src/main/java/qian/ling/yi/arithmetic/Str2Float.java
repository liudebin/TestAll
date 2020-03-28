package qian.ling.yi.arithmetic;

import org.junit.Assert;
import org.junit.Test;

public class Str2Float {

    public static float str2float(String str) throws Exception {
        if (null == str || "".equals(str)||str.contains(" ")) {
            throw new Exception("格式错误");
        }

        String[] strs = str.split("\\.");
        if (strs.length > 2) {
            throw new Exception("格式错误");
        }

        if (strs.length ==1) {
            return Integer.parseInt(str);
        }


        int tmp1 = Integer.parseInt(strs[0]);
        int tmp = Integer.parseInt(strs[1]);
        if (str.contains("-")) {
            tmp = -Integer.parseInt(strs[1]);
        }
        int length = strs[1].length();
        float mod = 10;
        for (int i = 1; i <length; i ++) {
            mod *=10;
        }

        float f = tmp/mod;
        return tmp1+f;
    }


    @Test
    public void test() throws Exception {
        Assert.assertEquals(1.1f, str2float("1.1"), 0.0);
        Assert.assertEquals(-1.1f, str2float("-1.1"), 0.0);
        Assert.assertEquals(2.123f, str2float("2.123"), 0.0);
    }
}
