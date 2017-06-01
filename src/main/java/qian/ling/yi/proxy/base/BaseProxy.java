package qian.ling.yi.proxy.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;
import qian.ling.yi.proxy.BaseInterfaceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by liuguobin on 2017/3/8.
 */
public class BaseProxy extends AbstractTest{

    @Test
    public void test() {
        Object a = Proxy.newProxyInstance(this.getClass().getClassLoader(), BaseInterfaceImpl.class.getInterfaces(), new InvocationHandler() {
             @Override
             public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                 method.invoke(proxy, args);
                 System.out.println("heh");
                 return null;
             }
         });

        System.out.println(a);
    }
}
