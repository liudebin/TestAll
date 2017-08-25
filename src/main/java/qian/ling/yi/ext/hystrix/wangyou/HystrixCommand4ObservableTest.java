package qian.ling.yi.ext.hystrix.wangyou;

import com.netflix.hystrix.*;
import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.TimeUnit;


/**
 * HystrixCommand的observe()与toObservable()的区别：
 * 1）observe()会立即执行HelloWorldHystrixCommand.run()；
 *  toObservable()要在toBlocking().single()或subscribe()时才执行HelloWorldHystrixCommand.run()
 * 2）observe()中，toBlocking().single()和subscribe()可以共存；
 *  在toObservable()中不行，因为两者都会触发执行HelloWorldHystrixCommand.run()，
 *  这违反了同一个HelloWorldHystrixCommand对象只能执行run()一次原则，且只能subscribe一次。
 * @throws Exception
 */
public class HystrixCommand4ObservableTest {
	
	@Test
	public void testObservable() throws Exception {

		Long s = System.nanoTime();
		// observe()是异步非堵塞性执行，同queue
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
		Observable<String> hotObservable = hystrixCommand.observe(); //运行run
		System.out.println("执行过observe");
//		TimeUnit.MILLISECONDS.sleep(5000); 用了他，就都是全阻塞
//		System.out.println("休眠结束");
		// toBlocking().single()是堵塞的
		System.out.println("hotObservable single结果：" + hotObservable.toBlocking().single()); // 后续会阻塞

        System.out.println("single");
		// 注册观察者事件
		// subscribe() 阻塞 看之前操作是否是toBlocking()操作
		hotObservable.subscribe(new Observer<String>() {

			// 先执行onNext再执行onCompleted
			 @Override
			public void onCompleted() {
				System.out.println("hotObservable completed");
			}

			 @Override
			public void onError(Throwable e) {
				e.printStackTrace();
			}

			/**
			 * 先注册的先执行？
			 * 执行的时候还会阻塞？
			 * @param v
			 */
			 @Override
			public void onNext(String v) {
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("hotObservable onNext: " + v);
			}

		});
		System.out.println("after subscribe");

		// 堵塞
		// - also verbose anonymous inner-class
		// - ignore errors and onCompleted signal
		hotObservable.subscribe(new Action1<String>() {

			// 相当于上面的onNext()
			// @Override
			public void call(String v) {
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("hotObservable call: " + v);
			}

		});
		System.out.println("after subscribe call");

		// 主线程不直接退出，在此一直等待其他线程执行
		System.in.read();

	}
	
	@Test
	public void testToObservable() throws Exception {

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

		// toObservable()是异步非堵塞性执行，同queue
		Observable<String> coldObservable = hystrixCommand.toObservable();
        System.out.println("执行过toObservable");
        // single()是堵塞的
//		System.out.println("Observable single结果：" + coldObservable.toBlocking().single());
//        System.out.println("执行过single");
		// 注册观察者事件
		// subscribe()是非堵塞的
		// - this is a verbose anonymous inner-class approach and doesn't do assertions
		coldObservable.subscribe(new Observer<String>() {

			// 先执行onNext再执行onCompleted
			// @Override
			public void onCompleted() {
				System.out.println("coldObservable completed");
			}

			// @Override
			public void onError(Throwable e) {
				System.out.println("coldObservable error");
				e.printStackTrace();
			}

			// @Override
			public void onNext(String v) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("coldObservable onNext: " + v);
			}

		});

        System.out.println("执行过subscribe");

		// 非堵塞
		// - also verbose anonymous inner-class
		// - ignore errors and onCompleted signal
//		coldObservable.subscribe(new Action1<String>() {
//
//			// 相当于上面的onNext()
//			// @Override
//			public void call(String v) {
//				System.out.println("coldObservable call: " + v);
//			}
//
//		});
        coldObservable.subscribe(new Action1<String>() {

            // 相当于上面的onNext()
            // @Override
            public void call(String v) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("hotObservable call: " + v);
            }

        });
        System.out.println("after subscribe call");

		// 主线程不直接退出，在此一直等待其他线程执行
		System.in.read();

	}

}
