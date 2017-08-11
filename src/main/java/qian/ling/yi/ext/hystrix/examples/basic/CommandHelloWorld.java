/**
 * Copyright 2012 Netflix, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package qian.ling.yi.ext.hystrix.examples.basic;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * The obligatory "Hello World!" showing a simple implementation of a {@link HystrixCommand}.
 */
public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
        //最少配置:指定命令组名(CommandGroup)
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        // 依赖逻辑封装在run()方法中
        try {
//            Thread.sleep(950L);
            Thread.sleep(100L);
            System.out.println("run");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello " + name + "!";
    }

    public static class UnitTest {

        @Test
        public void testSynchronous() {
//            CommandHelloWorld he = new CommandHelloWorld("World");
//            assertEquals("Hello World!", he.execute());
//            assertEquals("Hello World!", he.execute());
            //每个Command对象只能调用一次,不可以重复调用,
            //重复调用对应异常信息:This instance can only be executed once. Please instantiate a new instance.
//            command定义的超时时间,默认:1秒
//            execute = queue().get();
            assertEquals("Hello World!", new CommandHelloWorld("World").execute());
            assertEquals("Hello Bob!", new CommandHelloWorld("Bob").execute());
        }

        @Test
        public void testAsynchronous1() throws Exception {
//            queue = toObservable().toBlocking().toFuture()
            assertEquals("Hello World!", new CommandHelloWorld("World").queue().get());
            assertEquals("Hello Bob!", new CommandHelloWorld("Bob").queue().get());
        }

        @Test
        public void testAsynchronous2() throws Exception {

            Future<String> fWorld = new CommandHelloWorld("World").queue();
            Future<String> fBob = new CommandHelloWorld("Bob").queue();
            // 报超时异常
            assertEquals("Hello World!", fWorld.get(940, TimeUnit.MILLISECONDS));
            assertEquals("Hello Bob!", fBob.get(1, TimeUnit.MILLISECONDS));
        }

        @Test
        public void testObservable() throws Exception {
            //注册观察者事件拦截
            Observable<String> fWorld = new CommandHelloWorld("World").observe();
            Observable<String> fBob = new CommandHelloWorld("Bob").observe();
            System.out.println("1");
            TimeUnit.SECONDS.sleep(2); //会直接执行command方法
            System.out.println(2);
            // blocking
            assertEquals("Hello World!", fWorld.toBlocking().single());
            assertEquals("Hello Bob!", fBob.toBlocking().single());
            System.out.println("3");

            // non-blocking 
            // - this is a verbose anonymous inner-class approach and doesn't do assertions
            //注册结果回调事件
            fWorld.subscribe(new Observer<String>() {

                // onNext/onError完成之后最后回调
                @Override
                public void onCompleted() {
                    // nothing needed here
                    System.out.println("completed");
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                // 获取结果后回调
                @Override
                public void onNext(String v) {
                    System.out.println("onNext: " + v);
                }

            });

            // non-blocking
            // - also verbose anonymous inner-class
            // - ignore errors and onCompleted signal
            fBob.subscribe(new Action1<String>() {

                //执行结果处理,result 为HelloWorldCommand返回的结果
                //用户对结果做二次处理.
                @Override
                public void call(String v) {
                    System.out.println("call: " + v);
                }

            });

            // non-blocking
            // - using closures in Java 8 would look like this:
            
            //            fWorld.subscribe((v) -> {
            //                System.out.println("onNext: " + v);
            //            })
            
            // - or while also including error handling
            
            //            fWorld.subscribe((v) -> {
            //                System.out.println("onNext: " + v);
            //            }, (exception) -> {
            //                exception.printStackTrace();
            //            })
            
            // More information about Observable can be found at https://github.com/Netflix/RxJava/wiki/How-To-Use

        }
    }

    /**
     * 除了HystrixBadRequestException异常之外，所有从run()方法抛出的异常都算作失败，并触发降级getFallback()和断路器逻辑。

     HystrixBadRequestException用在非法参数或非系统故障异常等不应触发回退逻辑的场景。
     * @return
     */

    @Override
    protected String getFallback() {
        return "fallback";
    }
}
