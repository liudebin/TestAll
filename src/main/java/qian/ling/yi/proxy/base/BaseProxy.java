package qian.ling.yi.proxy.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;
import qian.ling.yi.proxy.BaseInterface;
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
        BaseInterface baseInterface = new BaseInterfaceImpl();
        //此处只能转换成 BaseInterface，而不能转换成被代理类
        BaseInterface a = (BaseInterface) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
//                new Class[]{BaseInterface.class},
                baseInterface.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("TagService代理前");
                    System.out.println(proxy.getClass().getSuperclass());
                    System.out.println(proxy.getClass().getInterfaces()[0].getName());
                    Object returnObject = method.invoke(baseInterface, args);
                    System.out.println("TagService代理后");
                    return returnObject;
                }
                );
        a.printA();
        //被代理类的方法，转换都出错，不能被执行。
//        a.printB();
    }

    class jdkInvocation implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }
}
