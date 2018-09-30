package qian.ling.yi.arithmetic.face;

import org.junit.Test;
import qian.ling.yi.AbstractTest;


/**
 * Created by guobin.liu@holaverse.com on 2018/9/26.
 */

public class countChar extends AbstractTest {

    @Test
    public void test() {
        String a  = "abbaaa";
        char[] chars = a.toCharArray();
        char tmp = '\u0000';
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            if (tmp == aChar) {
                i ++;
            }
            else {
                if (i == 1) {
                    sb.append(tmp);
                    tmp = aChar;
                    i = 1;
                } else if (i == 2) {
                    sb.append(tmp).append(tmp);
                    tmp = aChar;
                    i = 1;
                } else if (i > 2) {
                    sb.append(tmp).append(i);
                    i = 1;
                    tmp = aChar;
                } else {
                    tmp = aChar;
                    i = 1;
                }
            }
        }

        if (i == 1) {
            sb.append(tmp);
        }
        else if (i == 2) {
            sb.append(tmp).append(tmp);
        }
        else if(i > 2) {
            sb.append(tmp).append(i);
        }
        System.out.println(sb.toString());
    }

}
