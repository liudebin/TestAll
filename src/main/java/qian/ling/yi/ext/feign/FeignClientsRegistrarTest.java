package qian.ling.yi.ext.feign;

import org.junit.Test;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.mock.env.MockEnvironment;

/**
 * TODO
 *
 * @date: 2019/9/2.
 * @author: guobin.liu@holaverse.com
 */

//@PowerMockIgnore({"javax.crypto.*", "javax.management.*", "javax.security.*"})
//@PrepareForTest({ParamUtil.class, DictUtil.class})
@EnableFeignClients("qian.ling.yi.ext.feign")
public class FeignClientsRegistrarTest{

    @Test
    public void tt(){
        FeignClientsRegistrar feignClientsRegistrar = new FeignClientsRegistrar();
        feignClientsRegistrar.setEnvironment(new MockEnvironment());
        AnnotationMetadata classMetadata = new StandardAnnotationMetadata(FeignClientsRegistrarTest.class);
        BeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
        feignClientsRegistrar.registerBeanDefinitions( classMetadata, registry );

    }
}

