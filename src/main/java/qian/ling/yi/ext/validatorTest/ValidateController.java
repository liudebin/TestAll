package qian.ling.yi.ext.validatorTest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO
 *
 * @date: 2019/10/8.
 * @author: guobin.liu@holaverse.com
 */

@Controller
public class ValidateController {
    @RequestMapping("/validate")
    public String foo(
            @RequestBody
            @Validated DemoModel demoModel , @RequestBody BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            for (FieldError fieldError : bindingResult.getFieldErrors()) {

            }
            return "fail";
        }
        return "success";
    }
}
