package qian.ling.yi.ext.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;

import java.util.concurrent.Future;

/**
 * hello world
 *
 * @author liuguobin
 * @date 2017/8/8
 */

public class HelloHystrix extends HystrixCommand<String>{

    private final String name;

    public HelloHystrix(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "Hello " + name + "!";
    }

    public static void main(String[] args) {
        String s = new HelloHystrix("Bob").execute();
        Future<String> future = new HelloHystrix("Bob").queue();
        Observable<String> observable = new HelloHystrix("Bob").observe();
    }
}
