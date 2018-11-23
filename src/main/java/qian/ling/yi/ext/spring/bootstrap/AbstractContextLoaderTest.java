package qian.ling.yi.ext.spring.bootstrap;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuguobin on 2016/12/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml"})
//@ActiveProfiles("stb")
public abstract class AbstractContextLoaderTest {

}
