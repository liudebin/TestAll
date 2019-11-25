package qian.ling.yi.base.annotation.testAnnotationBeInherite.base;

import org.springframework.web.bind.annotation.PostMapping;
import qian.ling.yi.ext.feign.FeignClient;

import java.util.List;

/**
 * @author xuecheng.zhou
 */
@TestAnnotation(name = "rfs-app", url = "http://rfs-app${dnsDomain:}")
public interface RuleFrontFacade {

    @PostMapping("/RuleFrontFacade/submitModelRequest")
    List<String> submitModelRequest(String request);

    @PostMapping("/RuleFrontFacade/queryModelResults")
    List<String> queryModelResults(String query);

}
