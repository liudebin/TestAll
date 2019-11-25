package qian.ling.yi.ext.validatorTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @date: 2019/10/8.
 * @author: guobin.liu@holaverse.com
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

//    /**
//     * 登陆异常
//     * @param req
//     * @param e
//     * @return
//     * @throws AuthException
//     */
//    @ExceptionHandler(value = AuthException.class)
//    @ResponseBody
//    public DataResponse handleAuthException(HttpServletRequest req, AuthException e) throws AuthException {
//        DataResponse r = new DataResponse();
//        r.setResCode(e.getCode()+"");
//        r.setMsg(e.getMsg());
//        logger.info("AuthException",e.getMsg());
//        return r;
//    }

    /**
     * 验证异常
     * @param req
     * @param e
     * @return
     * @throws MethodArgumentNotValidException
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public DataResponse handleMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e) throws MethodArgumentNotValidException {
        DataResponse r = new DataResponse();
        BindingResult bindingResult = e.getBindingResult();
        String errorMesssage = "Invalid Request:\n";

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + "\n";
        }
        r.setResCode("VERIFICATION_DOES_NOT_PASS");
        r.setMsg(errorMesssage);
        logger.info("MethodArgumentNotValidException",e);
        return r;
    }

    /**
     * 全局异常
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public DataResponse handleException(HttpServletRequest req, Exception e) throws Exception {
        DataResponse r = new DataResponse();
        r.setResCode("CODE_ERROR");
        r.setMsg("CODE_ERROR"+e.getMessage());
        logger.error(e.getMessage(),e);
        return r;
    }

    @ExceptionHandler(value =BindException.class)
    @ResponseBody
    public DataResponse handleBindException(BindException e) throws BindException {
        // ex.getFieldError():随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，则调用ex.getAllErrors()
        FieldError fieldError = e.getFieldError();
        StringBuilder sb = new StringBuilder();
        sb.append(fieldError.getField()).append("=[").append(fieldError.getRejectedValue()).append("]")
                .append(fieldError.getDefaultMessage());
        // 生成返回结果
        DataResponse r = new DataResponse();
        r.setResCode("VERIFICATION_DOES_NOT_PASS");
        r.setMsg(sb.toString());
        logger.info("BindException", e);
        return r;
    }
}
