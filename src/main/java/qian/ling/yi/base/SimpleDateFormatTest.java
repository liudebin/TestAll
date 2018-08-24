package qian.ling.yi.base;

import org.junit.Test;

import java.text.SimpleDateFormat;

/**
 * SimpleDateFormatTest
 *
 * @author liuguobin
 * @date 2018/5/21
 */

public class SimpleDateFormatTest {

    @Test
    public void testError() throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHH");
//        simpleDateFormat.setLenient(false);
//        simpleDateFormat.setLenient(true);
        System.out.println(simpleDateFormat.parse("201709210009"));
    }
}
