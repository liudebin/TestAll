package qian.ling.yi.ext.spring.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by liuguobin on 2016/12/10.
 */
@Component
public class Bean {
    private Logger logger = LoggerFactory.getLogger(getClass());
    public void test() {
        logger.info("开始了");
    }
}
