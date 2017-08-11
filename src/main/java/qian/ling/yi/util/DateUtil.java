package qian.ling.yi.util;

import java.text.DateFormat;
import java.util.Date;

/**
 * DateUtil
 *
 * @author liuguobin
 * @date 2017/8/5
 */

public class DateUtil {

    public static String getCurrentDateTime() {
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        String s = dateFormat.format(new Date());
//        System.out.println(s);
        return s;
    }

    public static void main(String[] args) {
        DateUtil.getCurrentDateTime();
    }
}
