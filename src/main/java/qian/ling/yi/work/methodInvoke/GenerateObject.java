package qian.ling.yi.work.methodInvoke;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author xuecheng.zhou
 */
public class GenerateObject {
    public static void main(String[] args) {
        parseClassObjectData(Response.class);
    }


    public static <T> Optional<T> parseClassObjectDataOptional(Type type) {
        if (type == void.class) {
            return Optional.empty();
        }
        Class<T> classs;
        if (type instanceof Class) {
            classs = (Class<T>) type;
            if (isBasicAndEnum(classs)) {
                return (Optional<T>) getBasicValueOptional(classs);
            }
            return Optional.ofNullable(generateSimpleJavaBean(classs));
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            classs = (Class<T>) parameterizedType.getRawType();
            if (List.class.isAssignableFrom(classs)) {
                Type genericType = parameterizedType.getActualTypeArguments()[0];
                return Optional.ofNullable((T) ImmutableList.of(parseClassObjectData(genericType)));
            } else if (Set.class.isAssignableFrom(classs)) {
                Type genericType = parameterizedType.getActualTypeArguments()[0];
                return Optional.ofNullable((T) ImmutableSet.of(parseClassObjectData(genericType)));
            } else if (Map.class.isAssignableFrom(classs)) {
                Type genericKeyType = parameterizedType.getActualTypeArguments()[0];
                Type genericValType = parameterizedType.getActualTypeArguments()[1];
                Optional<ImmutableMap<Object, Object>> objectObjectImmutableMap = parseClassObjectDataOptional(genericKeyType).map(n ->
                        parseClassObjectDataOptional(genericValType).map(m -> ImmutableMap.of(n, m)).orElse(null));
                if (objectObjectImmutableMap.isPresent()) {
                    return (Optional<T>) objectObjectImmutableMap;
                }
                System.err.println(type + " 无法初始化");
                return Optional.empty();
//                return Optional.ofNullable((T) ImmutableMap.of(parseClassObjectData(genericKeyType), parseClassObjectData(genericValType)));
            } else if (classs == Response.class) {
                Response response = new Response<>().success();
                response.setData(parseClassObjectData(parameterizedType.getActualTypeArguments()[0]));
                return Optional.ofNullable((T) response);
            }
        }
        System.err.println(type + " 无法初始化");
        return Optional.empty();
    }

    private static Optional<Object> getBasicValueOptional(Class<?> type) {
        return Optional.ofNullable(getBasicValue(type));
    }


    public static <T> T parseClassObjectData(Type type) {
        if (type == void.class) {
            return null;
        }
        Class<T> classs;
        if (type instanceof Class) {
            classs = (Class<T>) type;
            if (isBasicAndEnum(classs)) {
                return (T) getBasicValue(classs);
            }
            return generateSimpleJavaBean(classs);
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            classs = (Class<T>) parameterizedType.getRawType();
            if (List.class.isAssignableFrom(classs)) {
                Type genericType = parameterizedType.getActualTypeArguments()[0];
                return (T) ImmutableList.of(parseClassObjectData(genericType));
            } else if (Set.class.isAssignableFrom(classs)) {
                Type genericType = parameterizedType.getActualTypeArguments()[0];
                return (T) ImmutableSet.of(parseClassObjectData(genericType));
            } else if (Map.class.isAssignableFrom(classs)) {
                Type genericKeyType = parameterizedType.getActualTypeArguments()[0];
                Type genericValType = parameterizedType.getActualTypeArguments()[1];
                return (T) ImmutableMap.of(parseClassObjectData(genericKeyType), parseClassObjectData(genericValType));
            } else if (classs == Response.class) {
                Response response = new Response<>().success();
                response.setData(parseClassObjectData(parameterizedType.getActualTypeArguments()[0]));
                return (T) response;
            }
//            else if (classs == Page.class) {
//                Page page = new Page();
//                page.setPage(1);
//                page.setPages(1);
//                page.setPageSize(1);
//                page.setTotal(1);
//                page.setRows(ImmutableList.of(parseClassObjectData(parameterizedType.getActualTypeArguments()[0])));
//                return (T) page;
//            }
//            else if (classs == PageResult.class) {
//                PageResult pageResult = new PageResult();
//                pageResult.setCount(1);
//                pageResult.setPageCount(1);
//                pageResult.setPageNo(1);
//                pageResult.setPageSize(1);
//                pageResult.setResultData(ImmutableList.of(parseClassObjectData(parameterizedType.getActualTypeArguments()[0])));
//                return (T) pageResult;
//            }
        }
//        else if (type instanceof TypeVariable) {
//            TypeVariable typeVariable = (TypeVariable) type;
//            //todo
//            return null;
//        }
        throw new RuntimeException(type.getTypeName());
    }

    private static <T> T generateSimpleJavaBean(Class<T> classs) {
        try {
            if (classs == Object.class) {
                return null;
            }
            T resultObject;
            if (classs.isArray()) {
                resultObject = (T) Array.newInstance(classs.getComponentType(), 1);
                Array.set(resultObject, 0, parseClassObjectData(classs.getComponentType()));
                return resultObject;
            } else {
                resultObject = classs.newInstance();
            }
            Field[] fields = classs.getDeclaredFields();
            for (Field f : fields) {
                if (Modifier.isFinal(f.getModifiers())) {
                    continue;
                }
                f.setAccessible(true);
                f.set(resultObject, parseClassObjectData(f.getGenericType()));
            }
            return resultObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object getBasicValue(Class<?> type) {
        if (type == Integer.class
                || type == Byte.class
                || type == Short.class
                || type == int.class
                || type == byte.class
                || type == short.class) {
            return 1;
        } else if (type == Long.class || type == long.class) {
            return 1L;
        } else if (type == Boolean.class || type == boolean.class) {
            return true;
        } else if (type == String.class) {
            return "1";
        } else if (type == String[].class) {
            return new String[]{"1"};
        } else if (type == Double.class || type == double.class) {
            return 1.0D;
        } else if (type == Float.class || type == float.class) {
            return 1.0F;
        } else if (type == BigDecimal.class) {
            return BigDecimal.ONE;
        } else if (type == Date.class) {
            return new Date();
//        } else if (type == Map.class || type == LinkedHashMap.class || type == HashMap.class) {
//            HashMap hashMap = new HashMap();
//            hashMap.put("key", "value");//TODO
//            return hashMap;
//        } else if (type == List.class || type == ArrayList.class) {
//            List list = new ArrayList();
//            list.add("value");
//            return list;
//        } else if (type == JSONObject.class ) {
//            return new JSONObject();
//        } else if (type == JSONArray.class ) {
//            return new JSONArray();
        } else if (isEnum(type)) {
            return type.getEnumConstants()[0];
        } else {
            throw new RuntimeException(type.getName());
        }
    }

    private static List<Class> basicDatas = new ArrayList<Class>() {
        {
            add(Byte.class);
            add(Character.class);
            add(Integer.class);
            add(Short.class);
            add(Long.class);
            add(Boolean.class);
            add(Double.class);
            add(Float.class);
            add(byte.class);
            add(boolean.class);
            add(char.class);
            add(short.class);
            add(int.class);
            add(long.class);
            add(float.class);
            add(double.class);
            add(String.class);
            add(String[].class);
            add(Date.class);
            add(BigDecimal.class);
            add(Map.class);
            add(LinkedHashMap.class);
            add(HashMap.class);
            add(List.class);
            add(ArrayList.class);
            add(JSONArray.class);
            add(JSONObject.class);
        }
    };

    private static boolean isBasicAndEnum(Class type) {
        if (basicDatas.contains(type) || isEnum(type)) {
            return true;
        }
        return false;
    }

    private static boolean isEnum(Class type) {
        return Objects.nonNull(type.getEnumConstants()) ? true : false;
    }

    private static <T> void setValue(T resultObject, Field f, Object value) throws IllegalAccessException {
        Class type = f.getType();
        if (type == Integer.class || type == Byte.class || type == Short.class
                || type == int.class || type == byte.class || type == short.class) {
            f.set(resultObject, 1);
        } else if (type == Long.class || type == long.class) {
            f.set(resultObject, 1L);
        } else if (type == Boolean.class || type == boolean.class) {
            f.set(resultObject, true);
        } else if (type == String.class) {
            f.set(resultObject, "1");
        } else if (type == Double.class || type == double.class) {
            f.set(resultObject, 1.0d);
        } else if (type == Float.class || type == float.class) {
            f.set(resultObject, 1.0f);
        } else if (type == BigDecimal.class) {
            f.set(resultObject, new BigDecimal("1.00"));
        } else if (type == Date.class) {
            f.set(resultObject, new Date());
        } else if (type == Map.class || type == LinkedHashMap.class || type == HashMap.class) {
            HashMap hashMap = new HashMap();
            hashMap.put("key", "value");
            f.set(resultObject, hashMap);
        } else if (type == List.class || type == ArrayList.class) {
            List list = new ArrayList();
            list.add("value");
            f.set(resultObject, list);
        } else if (type == JSONObject.class) {
            f.set(resultObject, new JSONObject());
        } else if (type == JSONArray.class) {
            f.set(resultObject, new JSONArray());
        } else if (isBasicAndEnum(type)) {
            f.set(resultObject, type.getEnumConstants()[0]);
        } else {
            f.set(resultObject, value);
        }
    }
}
