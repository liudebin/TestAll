package qian.ling.yi.ext.drools;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import qian.ling.yi.AbstractTest;

/**
 * Created by guobin.liu@holaverse.com on 2018/9/30.
 * @author liuguobin
 */

public class DroolsTest extends AbstractTest {
    @Test
    public void testHelloWorld() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("helloWorldSession");
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
