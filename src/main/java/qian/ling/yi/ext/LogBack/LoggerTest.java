package qian.ling.yi.ext.LogBack;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuguobin on 2016/10/9.
 */
public class LoggerTest{

    @Before
    public void before() {
//        LoggerFactory.getILoggerFactory().
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "/src/main/java/qian/ling/yi/ext/LogBack/";

        try {
//            configurator.doConfigure(filePath + "logback.xml");
//            configurator.doConfigure(filePath + "logback_package.xml");
//            configurator.doConfigure(filePath + "logback_name.xml");
            configurator.doConfigure(filePath + "logback_class.xml");
        } catch (JoranException e) {
            e.printStackTrace();
        }
//        final Logger log = LoggerFactory.getLogger(LoggerName.NamesrvLoggerName); // 获取配置文件中相应节点信息。
//        log.info("qunimade");
    }
    /**
     * 打印null
     *
     * hhanull
     */
    @Test
    public void testLoggerNull() {
//        Logger logger = LoggerFactory.getLogger("test");
        Logger logger = LoggerFactory.getLogger(LoggerTest.class);
//        Logger logger = LoggerFactory.getLogger("test1"); // 获取不存在的节点，会走root节点
        String h = null;
        logger.debug("hha{}", h);
    }
}
