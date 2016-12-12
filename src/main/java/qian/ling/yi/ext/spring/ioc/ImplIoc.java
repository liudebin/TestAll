package qian.ling.yi.ext.spring.ioc;

import org.springframework.stereotype.Component;

/**
 * Created by liuguobin on 2016/12/10.
 */
@Component
public class ImplIoc extends AbstractIOC{

    public void test() {
        logger.info("实现类");
        bean.test();
    }
}
