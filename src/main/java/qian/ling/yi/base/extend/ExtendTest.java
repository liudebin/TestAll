package qian.ling.yi.base.extend;

import org.junit.Test;

public class ExtendTest {
    @Test
    public void test(){
        Parent p = new Son();
        System.out.println(p.i);
        p.te();
        Son s = (Son) p;
        System.out.println(s.i);
        System.out.println(p.i);
    }
}
