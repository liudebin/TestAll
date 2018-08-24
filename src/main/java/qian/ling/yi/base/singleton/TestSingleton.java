package qian.ling.yi.base.singleton;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 测试方法
 *
 * @author liuguobin
 * @date 2018/5/21
 */

public class TestSingleton {

    static SimpleClass simpleClass;

    public void fuck() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        Class cl = Class.forName("java.lang.ClassLoader", false, Thread.currentThread().getContextClassLoader());
//        final Method findClass = this.getClass().getClassLoader().getParent().getClass().getDeclaredMethod("findLoadedClass", new Class[] { String.class });
        final Method findClass =cl.getDeclaredMethod("findLoadedClass", new Class[] { String.class });
        findClass.setAccessible(true);
        Object invoke = findClass.invoke(this.getClass().getClassLoader(), "qian.ling.yi.base.singleton.LazyLoadSingleFactory");
        System.out.println(invoke);


        invoke = findClass.invoke(this.getClass().getClassLoader(), "qian.ling.yi.base.singleton.SimpleClass");
        System.out.println(invoke);

        LazyLoadSingleFactory.getSingleton().test();
        invoke = findClass.invoke(this.getClass().getClassLoader(), "qian.ling.yi.base.singleton.LazyLoadSingleFactory");
        System.out.println(invoke);

        LazyLoadSingleFactory.getSingleton().test(); //类初始化一次

//
        SimpleClass.houha();
//        invoke = findClass.invoke(this.getClass().getClassLoader(), "qian.ling.yi.base.singleton.SimpleClass");
//        System.out.println(invoke);
//        simpleClass.houha();
//        invoke = findClass.invoke(this.getClass().getClassLoader(), "qian.ling.yi.base.singleton.SimpleClass");
//        System.out.println(invoke);


//       new SimpleClass().testSimple();
//
//
//        invoke = findClass.invoke(this.getClass().getClassLoader(), "qian.ling.yi.base.singleton.SimpleClass");
//        System.out.println(invoke);
//
//
//        invoke = findClass.invoke(this.getClass().getClassLoader(), "qian.ling.yi.base.singleton.NoInitStringClass");
//        System.out.println(invoke);
//        System.out.println(NoInitStringClass.a);
//        System.out.println(NoInitStringClass.b);
//        System.out.println(NoInitStringClass.D);
//        System.out.println(NoInitStringClass.c);// 引起  NoInitStringClass 加载
        invoke = findClass.invoke(this.getClass().getClassLoader(), "qian.ling.yi.base.singleton.NoInitStringClass");
        System.out.println(invoke);


    }

    public static void main(String[] args) throws Exception {
        final TestSingleton testSingleton = new TestSingleton();
        testSingleton.fuck();
        System.out.println(testSingleton.getClass().getClassLoader());
    }
}
