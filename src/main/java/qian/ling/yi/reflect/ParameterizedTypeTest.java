package qian.ling.yi.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * ParameterizedType
 *
 * @author liuguobin
 * @date 2018/6/12
 */

public class ParameterizedTypeTest<P, Q> {

    class A {}
    class B extends A {}

    private Class<P> entityClass;
    public ParameterizedTypeTest (){
//        Type type = getClass().getGenericSuperclass();
//        System.out.println("getClass() == " + getClass());
//        System.out.println("type = " + type);
//        Type trueType = ((ParameterizedType)type).getActualTypeArguments()[0];
//        System.out.println("trueType1 = " + trueType);
//        trueType = ((ParameterizedType)type).getActualTypeArguments()[1];
//        System.out.println("trueType2 = " + trueType);
//        this.entityClass = (Class<P>)trueType;
//        System.out.println("entityClass = " + entityClass);
//
//        System.out.println(((ParameterizedType)type).getRawType());
//
//        B t = new B();
//        type = t.getClass().getGenericSuperclass();
//
//        System.out.println("B is A's super class :" + ((ParameterizedType)type).getActualTypeArguments().length);
//        一个Bean类不会是ParameterizedType，只有代表这个Bean类的类型（Type）才可能是ParameterizedType
        Type[] ts,as;Type t;ParameterizedType p;
        Class<?> c = getClass();
        if ((ts = getClass().getGenericInterfaces()) != null) {
            for (int i = 0; i < ts.length; ++i) {

                if (((t = ts[i]) instanceof ParameterizedType) &&
                        ((p = (ParameterizedType)t).getRawType() ==
                                Comparable.class) &&
                        (as = p.getActualTypeArguments()) != null &&
                        as.length == 1 && as[0] == c) // type arg is c
                    System.out.println(c);
            }
        }
    }

}

 class ClassDemo extends ParameterizedTypeTest<MyClass, MyInvoke>{
    public static void main(String[] args) {
        ClassDemo classDemo = new ClassDemo();
    }
}


 class MyClass {

}


 class MyInvoke {

}

