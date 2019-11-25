package qian.ling.yi.ext.feign;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author xuecheng.zhou
 */
@FeignClient(name = "rfs-app", url = "http://rfs-app${dnsDomain:}")
public interface RuleFrontFacade {

    @PostMapping("/RuleFrontFacade/submitModelRequest")
    List<String> submitModelRequest(String  request);

    @PostMapping("/RuleFrontFacade/queryModelResults")
    List<String> queryModelResults(String query);

}
