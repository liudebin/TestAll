package qian.ling.yi.ext.RxJava;

import org.junit.Test;
import qian.ling.yi.AbstractTest;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func0;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * ObservableCreateMethodTest
 *
 * @date: 2019/8/28.
 * @author: guobin.liu@holaverse.com
 */

public class ObservableCreateMethodTest extends AbstractTest {
    @Test
    public void testRange(){
        Observable.range(10, 5);

//                .subscribe(integer -> logger.info("{}", integer));
    }


    @Test
    public void testDefer() throws ExecutionException, InterruptedException {
//        此处真正的 subscriber被包装成 SafeSubscriber 再包装成 subscriber
//        subscriber 是会被传递到最终的  onObservable 身上。 Observable会变，如defer 到 defer 调用工厂类生成 Observable，
//        因此其对应的 onObservable 也会变。
        final Observable<Integer> defer = Observable.defer(new Func0<Observable<Integer>>() {
            int i = 1;

            @Override
            public Observable<Integer> call() {
                int a = i++;
                int b = i++;
                try {
                    logger.info("开始休眠");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Observable.from(Arrays.asList(a, b));
            }
        });
//        defer.subscribe(s->{
//            try {
//                Thread.sleep(10);
//                logger.info("睡醒");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            logger.info("Hello " + s + "!");
//        });
        final Future<List<Integer>> listFuture = defer.toList().toBlocking().toFuture();
        logger.info("{}", listFuture.get());


        defer.subscribe(s->{
            try {
                Thread.sleep(5000);
                logger.info("睡醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("Hello " + s + "!");
        });


        logger.info("你妹");
    }


    /**
     * 定义一个 Observable Factory ，之后每次被订阅就会创建一个新的 Observable
     */
    @Test
    public void TestDefer() {

        final Observable<Object> defer = Observable.defer(() ->
                {
                    logger.info("new one");
                    return Observable.from(Arrays.asList("a","b"));}
        );
        subscriber(defer);

        defer.subscribe(System.out::println);

        subscriber(defer);
    }

    private void subscriber(Observable<Object> defer) {
        defer.subscribe((s) -> {
            logger.info("玩你自己的 5 s");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("玩玩了");
            logger.info("{}", s);

        });
    }

    @Test
    public void testJust()  {
        Observable.just(1,2,3).subscribe(System.out::println);
//        Observable
//                .just(Arrays.asList("a", "b")).subscribe(System.out::println);
    }

    @Test
    public void testFrom(){
        final Observable<String> observable = Observable.from(Arrays.asList("a", "b"));
        observable.subscribe(System.out::println);
    }

    @Test
    public void testInterval(){
        Observable.interval(1, TimeUnit.SECONDS).subscribe(System.out::println);
    }

    @Test
    public void testRepeat(){
        Observable.just(1,2,3).repeat(3).subscribe(a -> logger.info("{}", a));
        Observable.just(Arrays.asList(1,2,3)).repeat(3).subscribe(a -> logger.info("{}", a));
    }

    @Test
    public void testJustFuture() throws ExecutionException, InterruptedException {
        final Future<String> a = Observable
                .just("a")
                .toBlocking()
                .toFuture();
        logger.info(a.get());
    }


    @Test
    public void testFuture() throws ExecutionException, InterruptedException {

    }

    @Test
    public void testObserver() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                logger.info("Item: " + s);
            }

            @Override
            public void onCompleted() {
                logger.info("Completed!");
            }

            @Override
            public void onError(Throwable e) {
                logger.info("Error!");
            }
        };


        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                logger.info("subscriber commpleted");
            }

            @Override
            public void onError(Throwable e) {
                logger.info("subscriber on Error");
            }

            @Override
            public void onNext(String s) {
                logger.info("subscriber: " + s);
            }

            @Override
            public void onStart() {
                logger.info("start");
            }
        };

        Observable.just("a","b").subscribe(subscriber);
        Observable.just("a","b").subscribe(observer);
    }
}
