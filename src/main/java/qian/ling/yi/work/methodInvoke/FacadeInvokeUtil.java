package qian.ling.yi.work.methodInvoke;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * TODO
 *
 * @date: 2019/1/2.
 * @author: guobin.liu@holaverse.com
 */

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class FacadeInvokeUtil {
    @Autowired
    ApplicationContext ctx;

    @Test
    public void testNewAll() {
//        long count = ctx.getBeansWithAnnotation(FeignClient.class)
//                .entrySet()
//                .stream()
//                .filter(entry -> entry.getKey().contains("com.qihoo.finance.rds"))
//                .filter(entry -> entry.getKey().contains("com.qihoo.finance.msf"))
//                .filter(entry -> entry.getKey().contains("com.qihoo.finance.mes"))
//                .filter(entry -> entry.getKey().contains("com.qihoo.finance.dcs"))
//                .filter(entry -> entry.getKey().contains("com.qihoo.finance.rfs"))
//                .filter(entry -> entry.getKey().contains("com.qihoo.finance.lcs"))
//                .filter(entry -> entry.getKey().contains("com.qihoo.finance.fas"))
//                .peek(entry -> {
//                    try {
//                        testFacadeMethods2(Class.forName(entry.getKey()), entry.getValue());
//
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }).count();
//        System.out.println("遍历了 "+ count + "个 类" );
    }

    @Test
    public void testMethod(){
//        beTestMethod("RepayFacade", (m) -> true);
//        beTestMethod("EventInfoFacade", (m) -> true);
//        beTestMethod("DetectHandleFacade", (m) -> true);
//        beTestMethod("DetectHandleFacade", (m) -> m.getName().equals("getRelativeInfos"));
//        beTestMethod("ProductFacade", (m) -> m.getName().equals("queryProduct"));
//        beTestMethod("ManualFacade", (m) -> m.getName().equals("submitManual"));
//        beTestMethod("RepayFacade", (m) -> m.getName().equals("loanRpyCalc"));
        beTestMethod("ProductFacade", (m) -> m.getName().equals("queryProduct"));
    }





    private void beTestMethod(String className, Predicate<Method> methodPredicate) {
//        ctx.getBeansWithAnnotation(FeignClient.class)
//                .entrySet()
//                .stream()
//                .filter(entry -> entry.getKey().contains(className))
//                .forEach(entry -> {
//                    try {
//                        Class<?> aClass = Class.forName(entry.getKey());
//                        Object value = entry.getValue();
//
//                        long count = Arrays.stream(aClass.getDeclaredMethods())
//                                .filter(methodPredicate)
//                                .filter(m -> isMethodInvokeSud(true, value, className, m))
//                                .count();
//                        System.out.println(className + " 类 通过方法  " + count);
//
//
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                });
    }



    @Test
    public void testTest(){
        testFacadeMethods2( ApplicationContext.class, ctx);
    }

    public void testFacadeMethods2(Class tClass, Object obj) {
        Method[] methods = tClass.getMethods();
        String s = tClass.getSimpleName();

        int validPass = 0;
        int except = 0;
        for (Method method : methods) {

            if (IGNORE_LIST.contains(tClass.getName() + "." + method.getName())) {
                continue;
            }

            try {
                if (isMethodInvokeSud(false, obj, s, method)){
                    validPass++;
                }

            } catch (Exception e) {
                System.err.println(s + "  异常方法 " + method.getName());
                except++;
            }
        }

        System.out.println(s + "  方法数量  " + methods.length + "  测试通过数量 " + validPass + " 异常方法 " + except);
    }

    private Boolean isMethodInvokeSud(boolean withStacks, Object obj, String s, Method method) {
       return  initRequestParams(s, method)
               .map(args -> invokeTargetMethod(withStacks, s, method, obj, args))
               .filter(Optional::isPresent)
               .map(o -> isResponseTypeSame(o.get(), method, s))
               .orElse(false);
    }


    public void error(String str, int i) {
        if (i > 0) {
            System.err.println(str + i);
        }
    }

    private boolean isResponseTypeSame(Object o, Method n, String className) {
        try {
            TypeUtil.assertPropertyTypeSame(o, n.getGenericReturnType());
            if (n.getReturnType() != void.class) {
                TypeUtil.assertObjectNotEmpty(o);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(className + " 类 " + n.getName() + "  结果校验不通过");
            return false;
        }
    }

    private Optional invokeTargetMethod(boolean withStack, String className , Method m , Object o, Object ... objects) {
        try {
            Optional<Object> responseOptional = Optional.ofNullable(m.invoke(o, objects));
            if ( !responseOptional.isPresent()) {
                System.err.println(className + " 类 " + m.getName() + "  方法返回 为 null");
            }

            return responseOptional;
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.err.println(className + " 类 " + m.getName() + "  方法调用出错");
            if (withStack) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    private Optional<Object[]> initRequestParams(String s, Method n) {
        Object[] objects = Arrays.stream(n.getGenericParameterTypes())
                .map(GenerateObject::parseClassObjectDataOptional)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toArray();
        if (objects.length < n.getGenericParameterTypes().length) {
            System.err.println("类 "+ s + "方法 " + n.getName() + " 初始化错误");
            return Optional.empty();
        }
        return Optional.of(objects);
    }

    private static final List<String>  IGNORE_LIST = new ArrayList<String>(){
        {
//            add("com.qihoo.finance.apv.modules.apply.facade.ApAppFacade.queryAppInfo");
//            add("com.qihoo.finance.apv.modules.apply.facade.ApAppFacade.queryAppInfoList");
//            add("com.qihoo.finance.apv.modules.apply.facade.ApContactFacade.queryContactInfo");
//            add("com.qihoo.finance.apv.modules.apply.facade.ApContactFacade.queryContactInfoList");
//            add("com.qihoo.finance.apv.modules.apply.facade.ApContactFacade.compareContactNew");
//            add("com.qihoo.finance.apv.modules.apply.facade.ApplyFacade.queryYitu");
//            add("com.qihoo.finance.apv.modules.apply.facade.ApplyFacade.queryPboc");
//            add("com.qihoo.finance.apv.modules.common.facade.CreditCodeFacade.convertFromDictionary");
//            add("com.qihoo.finance.apv.modules.credit.facade.CreditFacade.getApplyCreditResult");
//            add("com.qihoo.finance.apv.modules.manual.facade.ApplyManualFacade.queryAppInfo");
//            add("com.qihoo.finance.apv.modules.manual.facade.ApplyManualFacade.queryMailListInfo");
//            add("com.qihoo.finance.apv.modules.manual.facade.ApplyManualFacade.queryAgentCallHostListInfo");
//            add("com.qihoo.finance.apv.modules.manual.facade.ApplyManualFacade.uploadBlacklist");
//            add("com.qihoo.finance.apv.modules.supply.facade.SupplyFacade.getSupplyBackUpData");
        }
    };
}
