package qian.ling.yi.ext.mock.mockito;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import qian.ling.yi.AbstractTest;

/**
 * TODO
 *
 * @date: 2018/10/22.
 * @author: guobin.liu@holaverse.com
 */

public class TestObject extends AbstractTest {
    InnerObject innerObject = new InnerObject();
    @Test
    public void test(){
        InnerObject i = Mockito.mock(InnerObject.class);
        i.a();
//        new InnerObject().a();
//        innerObject.a();
        TestObject t = Mockito.mock(TestObject.class);
        t.a();

    }

    public void a() {
        innerObject.a();
    }


    public class InnerObject {

        public void a() {
            System.out.println("A");
        }

        public void b() {
            System.out.println("B");
        }
    }
}
