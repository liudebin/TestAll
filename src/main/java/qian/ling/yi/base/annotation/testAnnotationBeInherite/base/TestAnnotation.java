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
public @interface TestAnnotation {
    String a() default "a";
    String name() default "a";
    String url() default "a";
}
