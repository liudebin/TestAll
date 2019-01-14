package qian.ling.yi.work.methodInvoke;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.stream.IntStream;

//@Aspect
//@Component
public class FacadeAspect {
//    @Pointcut("execution(public * com.qihoo.finance.*.modules.*.facade.*.*(..))")
    public void facadePointcut() {
    }

//    @Around("facadePointcut()")
    public Object invoke(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        Signature sig = pjp.getSignature();
        MethodSignature msig = (MethodSignature) sig;
        if (args != null && args.length > 0) {
            assertPropertyValueNotEmpty(args);
            Type[] parameterTypes = msig.getMethod().getGenericParameterTypes();
            assertPropertyTypeSame(args, parameterTypes);
        }
        return GenerateObject.parseClassObjectData(msig.getMethod().getGenericReturnType());
    }

    private void assertPropertyTypeSame(Object[] args, Type[] parameterTypes) {
        IntStream.range(0, args.length).forEach(i -> TypeUtil.assertPropertyTypeSame(args[i], parameterTypes[i]));
    }

    private void assertPropertyValueNotEmpty(Object[] args) {
        Arrays.asList(args).forEach(arg -> TypeUtil.assertObjectNotEmpty(arg));
    }
}
