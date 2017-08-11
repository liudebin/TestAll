package qian.ling.yi.ext.hystrix.wangyou.basic;

import com.netflix.hystrix.*;

import java.util.concurrent.TimeUnit;

public class HelloWorldHystrixCommand extends HystrixCommand<String> {

	private final String name;

	public HelloWorldHystrixCommand(String name) {
//		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("testCommandGroupKey"))  
                .andCommandKey(HystrixCommandKey.Factory.asKey("testCommandKey"))  
                /* 使用HystrixThreadPoolKey工厂定义线程池名称*/  
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("testThreadPool"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
//                		.withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)	// 信号量隔离
                		.withExecutionTimeoutInMilliseconds(5000))); //设置超时时间
//		HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true);
//		HystrixCollapserProperties.Setter()
//		HystrixThreadPoolProperties.Setter().withCoreSize(1);
        this.name = name;
	}
	
//	@Override  
//  protected String getFallback() {
//		System.out.println("触发了降级!");
//      return "exeucute fallback";
//  }

	@Override
	protected String run() throws InterruptedException {
//		for (int i = 0; i < 10; i++) {
//			System.out.println("runing HelloWorldHystrixCommand..." + i);
//		}
//		
		TimeUnit.MILLISECONDS.sleep(4000);
		return "Hello " + name + "! thread:" + Thread.currentThread().getName();
	}
}
