package qian.ling.yi.ext.spring.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liuguobin on 2016/12/10.
 */

public abstract class AbstractIOC {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    Bean bean;

    void test(){
        logger.info("抽象类");
        bean.test();
    }
}
