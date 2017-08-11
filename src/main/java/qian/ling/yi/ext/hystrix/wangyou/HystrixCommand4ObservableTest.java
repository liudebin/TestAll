package qian.ling.yi.ext.hystrix.wangyou;

import org.junit.Test;
import qian.ling.yi.ext.hystrix.wangyou.basic.HelloWorldHystrixCommand;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.TimeUnit;


/**
 * HystrixCommand的observe()与toObservable()的区别：
 * 1）observe()会立即执行HelloWorldHystrixCommand.run()；toObservable()要在toBlocking().single()或subscribe()时才执行HelloWorldHystrixCommand.run()
 * 2）observe()中，toBlocking().single()和subscribe()可以共存；在toObservable()中不行，因为两者都会触发执行HelloWorldHystrixCommand.run()，这违反了同一个HelloWorldHystrixCommand对象只能执行run()一次原则
 * @throws Exception
 */
public class HystrixCommand4ObservableTest {
	
	@Test
	public void testObservable() throws Exception {

		Long s = System.nanoTime();
		// observe()是异步非堵塞性执行，同queue
		Observable<String> hotObservable = new HelloWorldHystrixCommand("Hlx").observe();
		System.out.println((System.nanoTime() -s)/(60*1000*1000));
		// single()是堵塞的
		System.out.println("hotObservable single结果：" + hotObservable.toBlocking().single());

		// 注册观察者事件
		// subscribe()是非堵塞的 阻塞的吧
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
		System.out.println(System.nanoTime() -s);

		// 非堵塞
		// - also verbose anonymous inner-class
		// - ignore errors and onCompleted signal
		hotObservable.subscribe(new Action1<String>() {

			// 相当于上面的onNext()
			// @Override
			public void call(String v) {
				System.out.println("hotObservable call: " + v);
			}

		});
		System.out.println(System.nanoTime() -s);

		// 主线程不直接退出，在此一直等待其他线程执行
		System.in.read();

	}
	
//	@Test
	public void testToObservable() throws Exception {

		// toObservable()是异步非堵塞性执行，同queue
		Observable<String> coldObservable = new HelloWorldHystrixCommand("Hlx").toObservable();

		// single()是堵塞的
//		System.out.println("coldObservable single结果：" + coldObservable.toBlocking().single());
		
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
				System.out.println("coldObservable onNext: " + v);
			}

		});

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

		// 主线程不直接退出，在此一直等待其他线程执行
		System.in.read();

	}

}
