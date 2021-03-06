package qian.ling.yi.base.annotation.testAnnotationBeInherite.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

/**
 * TODO
 *
 * @date: 2018/10/17.
 * @author: guobin.liu@holaverse.com
 */

@TestAnnotation1("qian.ling.yi.base")
public class AnnotationTest extends AbstractTest {
    @Test
    public void test(){
        logger.info("{}", AnnotationTest.class.getAnnotations());
        logger.info("{}", AnnotationTest.class.getAnnotation(TestAnnotation.class));
        logger.info("{}", AnnotationTest.class.getAnnotation(TestAnnotation1.class));
        logger.info("{}", TestAnnotation1.class.getAnnotation(TestAnnotation.class));
    }

    public static void main(String[] args) {
        System.out.println(AnnotationTest.class.getAnnotatedSuperclass());
    }
}
