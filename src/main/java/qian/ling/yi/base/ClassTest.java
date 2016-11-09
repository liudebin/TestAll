package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * instanceof      s instanceof String  自身实例或子类实例 与 类名
 *isInstance      String.class.isInstance(s)) 自身类.class.isInstance(自身实例或子类实例)
 * isAssignableFrom     Object.class.isAssignableFrom(ArrayList.class) 。父类到子类，是class跟class之间的关系

 *构造函数不是普通方法，调用getDeclaredMethods 不会获得到。
 * Created by liuguobin on 2016/11/9.
 */
public class ClassTest extends AbstractTest {

    @Test
    public void testInstanceOf() {
        ArrayList<String> arrayList = new ArrayList<>();
        Object tmp = arrayList;
        logger.info("{}", tmp instanceof List);
        logger.info("{}", tmp instanceof LinkedList);
    }

    @Test
    public void testAssignable() {
        ArrayList<String> arrayList = new ArrayList<>();
        Object tmp = arrayList;
        logger.info("{}", Object.class.isAssignableFrom(tmp.getClass()));
        logger.info("{}", LinkedList.class.isAssignableFrom(tmp.getClass()));
        logger.info("{}", ArrayList.class.isAssignableFrom(tmp.getClass()));

    }

}
