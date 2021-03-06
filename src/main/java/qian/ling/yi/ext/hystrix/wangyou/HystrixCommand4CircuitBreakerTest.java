package qian.ling.yi.ext.hystrix.wangyou;

import com.netflix.hystrix.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 熔断器启用后
 * 请求满足一定的规则后，会直接进入fallback方法。
 *  规则  各规则 与 的关系
 *      CircuitBreakerRequestVolumeThreshold设置为3，意味着10s内请求超过3次就触发熔断器
 *
 * run()中无限循环使命令超时进入fallback，执行3次run后，将被熔断，进入降级，即不进入run()而直接进入fallback
 * 如果未熔断，但是threadpool被打满，仍然会降级，即不进入run()而直接进入fallback
 */
public class HystrixCommand4CircuitBreakerTest extends HystrixCommand<String> {
    static Logger logger  = LoggerFactory.getLogger(HystrixCommand4CircuitBreakerTest.class);
    private final String name;

    public HystrixCommand4CircuitBreakerTest(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CircuitBreakerTestGroup"))  
                .andCommandKey(HystrixCommandKey.Factory.asKey("CircuitBreakerTestKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("CircuitBreakerTest"))
                .andThreadPoolPropertiesDefaults(	// 配置线程池
                		HystrixThreadPoolProperties.Setter()
                		.withCoreSize(2)	// 配置线程池里的线程数，设置足够多线程，以防未熔断却打满threadpool
                )
                .andCommandPropertiesDefaults(	// 配置熔断器 条件要全部满足
                		HystrixCommandProperties
								.Setter()
                                .withExecutionTimeoutInMilliseconds(500) // 请求超时时间
								//是否启用熔断器,默认true. 启动 private final HystrixProperty circuitBreakerEnabled;
								.withCircuitBreakerEnabled(true)
								//统计时间 （默认10s）
//								.withMetricsRollingStatisticalWindowInMilliseconds()
								// 也就是在metricsRollingStatisticalWindowInMilliseconds（默认10s）内至少请求20次，熔断器才发挥起作用;

                                // 熔断器在 一个统计时间内请求的数量 是否达到开启的阀值，默认20。
                                .withCircuitBreakerRequestVolumeThreshold(12) // 当每10秒请求数量 达到 几12个 熔断器开始生效
								//默认:50%。当出错率超过50%后熔断器启动
                				.withCircuitBreakerErrorThresholdPercentage(80)
								// 熔断时间窗口，默认:5秒.熔断器中断请求5秒后会进入半打开状态,放下一个请求进来重试，如果该请求成功就关闭熔断器，否则继续等待一个熔断时间窗口
								.withCircuitBreakerSleepWindowInMilliseconds(5)
								//是否强制开启熔断器阻断所有请求,默认:false,不开启。置为true时，所有请求都将被拒绝;
                				//.withCircuitBreakerForceOpen(true)	// 置为true时，所有请求都将被拒绝，直接到fallback
								//是否允许熔断器忽略错误,默认false, 不开启 ，为true的话就不会熔断了。
//                				.withCircuitBreakerForceClosed(true)	// 置为true时，将忽略错误
                				//.withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)	// 信号量隔离
                )
        );
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        System.out.println("running run():" + name);
    	int num = Integer.valueOf(name);
    	if(num % 2 == 0 && num < 10) {	// 直接返回
    		return name;
    	}
    	else {
            TimeUnit.MILLISECONDS.sleep(600); //设置休眠时间。 - 与请求允许的时间相关
//        	while (true) {
//        		j++;
//        	}
    	}
		return name;
    }

    @Override
    protected String getFallback() {
        return "CircuitBreaker fallback: " + name;
    }

    public static class UnitTest {

        @Test
        public void testSynchronous() throws IOException {
            logger.info("start");
        	for(int i = 0; i < 50; i++) {
	        	try {
//	        		System.out.println("===========" + new HystrixCommand4CircuitBreakerTest(String.valueOf(i)).execute());
	        		System.out.println("===========" + new HystrixCommand4CircuitBreakerTest(String.valueOf(i)).queue());
                } catch(Exception e) {
	        		System.out.println("run()抛出HystrixBadRequestException时，被捕获到这里" + e.getCause());
	        	}
        	}

        	System.out.println("------开始打印现有线程---------");
        	Map<Thread, StackTraceElement[]> map=Thread.getAllStackTraces();
        	for (Thread thread : map.keySet()) {
				System.out.println(thread.getName());
			}
        	System.out.println("thread num: " + map.size());
        	
        	System.in.read();
        }
    }

}