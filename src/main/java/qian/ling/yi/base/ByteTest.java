package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;
import qian.ling.yi.util.ByteUtil;

/**
 * Byte
 *
 * @author liuguobin
 * @date 2018/4/28
 */

public class ByteTest extends AbstractTest{

    @Test
    public void test1() {
//        byte[] b = new byte[]{0, 0, 0, 52};
        byte[] b = new byte[]{0, 0, 1, -119};
//        byte[] b = new byte[]{0, 0, 0, 47};
        int i = b[1] & 0xFF | (b[0] & 0xFF) << 8 ;
        System.out.println(i);
        System.out.println(ByteUtil.bytes2Int(b));
    }
}
