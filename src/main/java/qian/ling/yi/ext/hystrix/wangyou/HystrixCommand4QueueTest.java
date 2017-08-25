package qian.ling.yi.ext.hystrix.wangyou;

import com.netflix.hystrix.*;
import org.junit.Test;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public class HystrixCommand4QueueTest {

	@Test
	public void testQueue() throws Exception {
		// queue()是异步非堵塞性执行：直接返回，同时创建一个线程运行HelloWorldHystrixCommand.run()
		// 一个对象只能queue()一次
		// queue()事实上是toObservable().toBlocking().toFuture()
        HystrixCommand<String> hystrixCommand = new HystrixCommand<String>(
                HystrixCommand
                        .Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey("testCommandGroupKey"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("testCommandKey"))
                        /* 使用HystrixThreadPoolKey工厂定义线程池名称*/
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("testThreadPool"))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
//                		        .withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)	// 信号量隔离
                                        .withExecutionTimeoutInMilliseconds(5000))) {
            @Override
            protected String run() throws Exception {
                System.out.println("start");
                TimeUnit.MILLISECONDS.sleep(4000);
                return "Hello " + "! thread:" + Thread.currentThread().getName();
            }
        };

        Future<String> future = hystrixCommand.queue();


		// 使用future时会堵塞，必须等待HelloWorldHystrixCommand.run()执行完返回
		String queueResult = future.get(10000, TimeUnit.MILLISECONDS);
		// String queueResult = future.get();
		System.out.println("queue异步结果：" + queueResult);
		assertEquals("Hello", queueResult.substring(0, 5));
		// This instance can only be executed once. Please instantiate a new instance.
        future = hystrixCommand.queue();
	}


}
