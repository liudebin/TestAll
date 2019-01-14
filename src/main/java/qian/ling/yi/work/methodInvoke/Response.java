
package qian.ling.yi.work.methodInvoke;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.Serializable;

public class Response<T> implements Serializable {

    @Test
    public void teset(){
        Response<String> response = new Response<>();
        response = response.success();
        response = response.success("");
    }
    private static final long serialVersionUID = 3715837692826438329L;
    private String flag;
    private String code;
    private String msg;
    private T data;

    public  Response() {
    }

    public Response<T> success() {
        this.flag = RespFlagEnum.SUCCESS.getCode();
        return this;
    }

    public  Response<T> success(T data) {
        this.data = data;
        return this.success();
    }



    public boolean checkIfSuccess() {
        return RespFlagEnum.SUCCESS.getCode().equals(this.flag);
    }

    public boolean checkIfFail() {
        return !this.checkIfSuccess();
    }

    public Response<T> fail() {
        this.flag = RespFlagEnum.FAIL.getCode();
        return this;
    }

    public Response<T> fail(Response<T> response) {
        this.code = response.getCode();
        this.msg = response.getMsg();
        return this.fail();
    }

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
