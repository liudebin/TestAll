package qian.ling.yi.ext.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * 注解测试
 *
 * @author liuguobin
 * @date 2017/5/20
 */

public class AnnotationTest {
//    @Mock
    @InjectMocks
    BaseBeanBase baseBeanBase;

//    @Mock //class qian.ling.yi.ext.mockito.BaseBean$MockitoMock$581148402
    @InjectMocks // 会选择参数最多的构造方法
    BaseBean baseBean;

    @Before
    public void setUp() {
//        logger.info("1");
        System.out.println("1 = " + 1);
        MockitoAnnotations.initMocks(this);
        System.out.println(2);
    }

    @Test
    public void test() {
        System.out.println(baseBean.getClass().toString());
        System.out.println("baseBean.getBaseBeanBase() = " + baseBean.getBaseBeanBase());

    }
}
