package qian.ling.yi.ext.RxJava;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
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

public class RxJavaTest {
    @Test
    public void testHello() {
        final Subscription subscribe = Observable.
                from(Arrays.asList("a", "b"))
                .subscribe(s -> {
            try {
                Thread.sleep(5000);
                System.out.println("睡醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Hello " + s + "!");
        });

        System.out.println(subscribe.isUnsubscribed());
        subscribe.unsubscribe();
        System.out.println("你妹");
    }

    @Test
    public void testSubscriber() {
        final Subscription subscribe = Observable
                .from(Arrays.asList("a", "b"))
                .subscribe(
                        new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                System.out.println("custom onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                System.out.println("custom error");
                            }
                            @Override
                            public void onNext(String s) {
                                System.out.println("Hello + " + s + "!");
                            }

                            @Override
                            public void onStart() {
                                try {
                                    Thread.sleep(5000);
                                    System.out.println("睡醒");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });


        System.out.println(subscribe.isUnsubscribed());
        subscribe.unsubscribe();
        System.out.println("你妹");
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
                    System.out.println("开始休眠");
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
//                System.out.println("睡醒");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("Hello " + s + "!");
//        });
        final Future<List<Integer>> listFuture = defer.toList().toBlocking().toFuture();
        System.out.println(listFuture.get());


        defer.subscribe(s->{
            try {
                Thread.sleep(5000);
                System.out.println("睡醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello " + s + "!");
        });


        System.out.println("你妹");
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
//            System.out.println(integerFuture.get());
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
            System.out.println("到了");
            System.out.println(JSON.toJSON(listFuture.get()));
            System.out.println(JSON.toJSON(listFuture.get().getClass()));
            System.out.println(JSON.toJSON(listFuture.get()));
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
                System.out.println("睡会儿 10 s");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("睡好了");
            System.out.println("Hello " + s + "!");
        };
//        定义，但不调用
        final Observable<String> observable = Observable.from(Arrays.asList("a", "b"));

//      在Observable订阅订阅者，调用 Observable
        observable.subscribe(onNext); // 此处就阻塞了？
        System.out.println("玩你自己的 5 s");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("玩玩了");
    }

    /**
     * 定义一个 Observable Factory ，之后每次被订阅就会创建一个先的 Observable
     */
    @Test
    public void TestDefer() {

        final Observable<Object> defer = Observable.defer(() ->
                {
                    System.out.println("new one");
                return Observable.from(Arrays.asList("a","b"));}
        );
        defer.subscribe((s) -> {
            System.out.println("玩你自己的 5 s");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("玩玩了");
            System.out.println(s);

        });

        defer.subscribe(System.out::println);
    }

    @Test
    public void testJust() throws ExecutionException, InterruptedException {
        Observable
                .just("a").subscribe(System.out::println);
    }

    @Test
    public void testJustFuture() throws ExecutionException, InterruptedException {
        final Future<String> a = Observable
                .just("a")
                .toBlocking()
                .toFuture();
        System.out.println(a.get());
    }


    @Test
    public void testFuture() throws ExecutionException, InterruptedException {

    }

    @Test
    public void testObserver() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                System.out.println("Item: " + s);
            }

            @Override
            public void onCompleted() {
                System.out.println("Completed!");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Error!");
            }
        };


        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("subscriber commpleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("subscriber on Error");
            }

            @Override
            public void onNext(String s) {
                System.out.println("subscriber: " + s);
            }

            @Override
            public void onStart() {
                System.out.println("start");
            }
        };

        Observable.just("a","b").subscribe(subscriber);
        Observable.just("a","b").subscribe(observer);
    }

//    @Test
//    public void testRxJava2() {
//        Flowable.just("Hello RxJava 2").subscribe(System.out::println);
//    }


}
