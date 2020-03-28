package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.lang.reflect.Constructor;

/**
 * 内部类的初始化
 *
 * @author liuguobin
 * @date 2018/1/5
 */

public class InnerClassInstance extends AbstractTest{
    /**
     * 内部类不能使用 class.newInstance();
     * @throws Exception
     */
    @Test
    public void testTmp() throws Exception {
        String s = "count,20,weight,2";
//        InnerClassPojo.InnerClass innerClass = InnerClassPojo.InnerClass.class.newInstance();
        final Constructor<?>[] declaredConstructors = InnerClassPojo.InnerClass.class.getDeclaredConstructors();
        final Object o = declaredConstructors[0].newInstance(new InnerClassPojo());
        System.out.println(((InnerClassPojo.InnerClass)o).getCount());
    }


}
