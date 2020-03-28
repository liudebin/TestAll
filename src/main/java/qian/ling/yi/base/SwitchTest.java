package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

/**
 * Created by liuguobin
 */
public class SwitchTest extends AbstractTest {

    @Test
    public void test() {
        String s = null;
        switchTestNull(s);
    }

    private void switchTestNull(String s) {
        switch (s) {
            case "null":
                System.out.println("null");
                break;
            case "":
                System.out.println("1");
                break;
            default:
                System.out.println("default");
        }
        System.out.println("end");
    }
}
