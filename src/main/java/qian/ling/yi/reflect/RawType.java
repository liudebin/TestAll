package qian.ling.yi.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author liuguobin
 * @date 2018/6/12
 */

public class RawType {
    public static void main(String args[]) throws Exception {

        Type type = StringList.class.getGenericSuperclass();
        System.out.println(type);
        ParameterizedType pt = (ParameterizedType) type;
//        返回声明了这个类型的类或接口，也就是去掉了泛型参数部分的类型对象。
        Type rawType = pt.getRawType();
        System.out.println(rawType);
//getActualTypeArguments()以数组的形式返回泛型参数列表。
        System.out.println(pt.getActualTypeArguments()[0]);


    }
    class StringList extends ArrayList<String> {
    }
}
