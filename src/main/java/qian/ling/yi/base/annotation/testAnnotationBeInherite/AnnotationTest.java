package qian.ling.yi.base.annotation.testAnnotationBeInherite;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import qian.ling.yi.AbstractTest;
import qian.ling.yi.base.annotation.testAnnotationBeInherite.base.TestAnnotation;
import qian.ling.yi.base.annotation.testAnnotationBeInherite.base.TestAnnotation1;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

/**
 * TODO
 *
 * @date: 2018/10/17.
 * @author: guobin.liu@holaverse.com
 */

@TestAnnotation1
public class AnnotationTest extends AbstractTest {
    @Test
    public void test(){
        logger.info("{}", BeAnnotaionBean.class.getAnnotations());
        assertThat(Arrays.asList(BeAnnotaionBeanSun.class.getAnnotations()), CoreMatchers.hasItems(BeAnnotaionBean.class.getAnnotations()[0]));
    }

    public static void main(String[] args) {
        System.out.println(AnnotationTest.class.getAnnotatedSuperclass());
    }
}
