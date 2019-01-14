package qian.ling.yi.work.methodInvoke;

import org.junit.Assert;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;

public class TypeUtil {
    public static void assertPropertyTypeSame(Object arg, Type type) {
        Class<?> paramType = null;
        if (type instanceof Class) {
            paramType = (Class<?>) type;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            paramType = (Class<?>) parameterizedType.getRawType();
            if (arg instanceof List) {
                Type genericType = parameterizedType.getActualTypeArguments()[0];
                assertPropertyTypeSame(((List) arg).get(0), genericType);
            } else if (arg instanceof Set) {
                Type genericType = parameterizedType.getActualTypeArguments()[0];
                assertPropertyTypeSame(((Set) arg).toArray()[0], genericType);
            } else if (arg instanceof Map) {
                Type genericKeyType = parameterizedType.getActualTypeArguments()[0];
                Type genericValType = parameterizedType.getActualTypeArguments()[1];
                assertPropertyTypeSame(((Map) arg).keySet().toArray()[0], genericKeyType);
                assertPropertyTypeSame(((Map) arg).values().toArray()[0], genericValType);
            } else if (arg instanceof Response) {
                assertPropertyTypeSame(((Response) arg).getData(), parameterizedType.getActualTypeArguments()[0]);
            }
//            else if (arg instanceof Page) {
//                assertPropertyTypeSame(((Page) arg).getRows(), parameterizedType.getActualTypeArguments()[0]);
//            }
        }
        if (paramType == Object.class || paramType.isPrimitive()) return;
        if (paramType.isArray() || paramType.isEnum() || paramType == String.class || paramType == BigDecimal.class
                || paramType == Date.class || paramType == Boolean.class || paramType == Character.class
                || paramType == Short.class || paramType == Long.class || paramType == Integer.class || paramType == Double.class || paramType == Float.class || paramType == Byte.class
                || Map.class.isAssignableFrom(paramType) || Collection.class.isAssignableFrom(paramType)) {
            paramType.cast(arg);
            return;
        }
        try {
            //simple java bean(contains getter/setter method)
            Object target = paramType.newInstance();
            BeanUtils.copyProperties(arg, target);
            assertObjectNotEmpty(target);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }


    public static void assertObjectNotEmpty(Object arg) {
        Assert.assertTrue(arg != null);
        Arrays.asList(arg.getClass().getDeclaredMethods()).stream().filter(method -> method.isAccessible() && method.getName().startsWith("get")).forEach(method -> {
            try {
                assertObjectNotEmpty(method.invoke(arg));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}