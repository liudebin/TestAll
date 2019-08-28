package qian.ling.yi.ext.RxJava;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import qian.ling.yi.AbstractTest;
import rx.*;
import rx.functions.Action1;
import rx.functions.Func0;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * RxJava
 *
 * @author liuguobin
 * @date 2018/3/14
 */

public class RxJavaTest extends AbstractTest {
    @Test
    public void testHello() {
        final Subscription subscribe = Observable.
                from(Arrays.asList("a", "b"))
                .subscribe(s -> {
            try {
                Thread.sleep(5000);
                logger.info("睡醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            logger.info("Hello " + s + "!");
        });

        logger.info("{}", subscribe.isUnsubscribed());
        subscribe.unsubscribe();
        logger.info("你妹");
    }

    @Test
    public void testSubscriber() {
        final Subscription subscribe = Observable
                .from(Arrays.asList("a", "b"))
                .subscribe(
                        new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                logger.info("custom onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                logger.info("custom error");
                            }
                            @Override
                            public void onNext(String s) {
                                logger.info("Hello + " + s + "!");
                            }

                            @Override
                            public void onStart() {
                                try {
                                    Thread.sleep(5000);
                                    logger.info("睡醒");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });


        logger.info("{}", subscribe.isUnsubscribed());
        subscribe.unsubscribe();
        logger.info("你妹");
    }



    @Test
    public void TestBlocking() {
//        final Observable<Integer> defer = Observable.defer(new Func0<Observable<Integer>>() {
//            int i = 1;
//
//            @Override
//            public Observable<Integer> call() {
//                int a = i++;
//                return Observable.from(Arrays.asList(a));
//            }
//        });
//
//        final Future<Integer> integerFuture = defer.toBlocking().toFuture();
//        try {
//            logger.info(integerFuture.get());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }


        final Observable<Integer> deferObservable = Observable.defer(new Func0<Observable<Integer>>() {
            int i = 1;

            @Override
            public Observable<Integer> call() {
                int a = i++;
                int b = i++;
                int c = i++;
                int d = i++;
//                try {
//                    Thread.sleep(5000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                return Observable.from(Arrays.asList(a,b,c,d));
            }
        });
        // 此处阻塞，并且会获取一个固定的值。
        final Future<List<Integer>> listFuture = deferObservable
                .toList() // Observable
                .toBlocking()
                .toFuture();

        try {
            logger.info("到了");
            logger.info("{}", JSON.toJSON(listFuture.get()));
            logger.info("{}", JSON.toJSON(listFuture.get().getClass()));
            logger.info("{}", JSON.toJSON(listFuture.get()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }




    /**
     * 设想的是 先睡，然后玩，玩完，睡醒。
     * 结果是 睡，睡好。睡，睡好。玩。玩完。怎么会有阻塞呢？
     *
     * 不是异步，而是定义异步方法？
     */
    @Test
    public void easyUnderStand() {
//        定义，但不调用
        final Action1<String > onNext = s -> {
            try {
                logger.info("睡会儿 10 s");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("睡好了");
            logger.info("Hello " + s + "!");
        };
//        定义，但不调用
        final Observable<String> observable = Observable.from(Arrays.asList("a", "b"));

//      在Observable订阅订阅者，调用 Observable
        observable.subscribe(onNext); // 此处就阻塞了？
        logger.info("玩你自己的 5 s");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("玩玩了");
    }



//    @Test
//    public void testRxJava2() {
//        Flowable.just("Hello RxJava 2").subscribe(System.out::println);
//    }


}
