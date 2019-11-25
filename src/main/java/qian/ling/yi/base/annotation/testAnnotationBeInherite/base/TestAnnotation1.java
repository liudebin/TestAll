package qian.ling.yi.base.annotation.testAnnotationBeInherite.base;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * TODO
 *
 * @date: 2018/10/17.
 * @author: guobin.liu@holaverse.com
 */

@Retention(RetentionPolicy.RUNTIME)
//@TestAnnotation
public @interface TestAnnotation1{
    String z() default "z";
    String[] value() default {};
    String[] basePackages() default {};
    Class<?>[] basePackageClasses() default {};
}
