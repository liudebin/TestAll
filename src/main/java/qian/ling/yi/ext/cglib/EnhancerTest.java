package qian.ling.yi.ext.cglib;

import org.junit.Test;
import org.springframework.cglib.proxy.*;
import qian.ling.yi.AbstractTest;
import qian.ling.yi.ext.cglib.base.BaseBeanForCglib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * EnhancerTest
 *
 * @author liuguobin
 * @date 2018/7/21
 */

public class EnhancerTest extends AbstractTest {

    private static final Class<?>[] CALLBACK_TYPES = new Class<?>[]
            {org.springframework.cglib.proxy.MethodInterceptor.class};


    @Test
    public void testEnhancer() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BaseBeanForCglib.class);
//        enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
        enhancer.setCallbackFilter(new  MethodOverrideCallbackFilter());
        enhancer.setCallbackTypes(CALLBACK_TYPES);   // createClass() 的必需品
        System.out.println(enhancer.createClass());
        enhancer.setCallback(new MethodInterceptorImpl()); // 如果设定了callbackType  fileter 也是必要的。
//        System.out.println(enhancer.createClass().getDeclaredConstructors()[0].newInstance(null));
        final Object o = enhancer.create();
        ((BaseBeanForCglib) o).beOverride();

    }

    private static class MethodOverrideCallbackFilter implements CallbackFilter {

        @Override
        public int accept(Method method) {
            System.out.println("Override for '" + method.getName() + "");
            return 0;
        }
    }




    private static class MethodInterceptorImpl implements MethodInterceptor {

        @Override
        public Object intercept(Object obj, Method method, Object[] args,
                                MethodProxy proxy) throws Throwable {
            System.out.println("Before invoke " + method);
            Object result = proxy.invokeSuper(obj, args);
            System.out.println("After invoke" + method);
            return result;
        }

    }
}
