package qian.ling.yi.ext.drools;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;
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

    @Test
    public void testStudent() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("helloWorldSession");
        Student student = new Student();
        student.setAge(22);
        student.setName("name");
        kieSession.insert(student);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
