package qian.ling.yi.thread.sourceRead;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

/**
 * Created by guobin.liu@holaverse.com on 2018/9/28.
 */

public class InhertThreadLocalTest extends AbstractTest {
    private static final ThreadLocal<InhertThreadLocalTest> context = new InheritableThreadLocal<InhertThreadLocalTest>() {
        @Override
        protected InhertThreadLocalTest childValue(InhertThreadLocalTest parentValue) {
            InhertThreadLocalTest context = new InhertThreadLocalTest();
            if (parentValue != null) {
                System.out.println(parentValue);
            }

            return context;
        }

        @Override
        protected InhertThreadLocalTest initialValue() {
            return new InhertThreadLocalTest();
        }
    };

    @Override
    public String toString() {
        return super.toString();
    }

    public static  InhertThreadLocalTest get() {
        return context.get();
    }

    @Test
    public void test() {
        InhertThreadLocalTest inhertThreadLocalTest = InhertThreadLocalTest.get();
        System.out.println(inhertThreadLocalTest);
    }
}
