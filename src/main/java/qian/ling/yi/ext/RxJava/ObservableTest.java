package qian.ling.yi.ext.RxJava;

import org.junit.Test;
import qian.ling.yi.AbstractTest;
import rx.Observable;
import rx.Subscriber;

import java.util.Arrays;
import java.util.Random;

/**
 * TODO
 *
 * @date: 2019/8/22.
 * @author: guobin.liu@holaverse.com
 */

public class ObservableTest extends AbstractTest {


    @Test
    public void createObserver(){

        Observable<Integer> integerObservable = Observable.create(subscriber -> {

            if (!subscriber.isUnsubscribed()) {
                for (int i = 0; i < 5; i++) {
                    int temp = new Random().nextInt(10);
                    // 如果value > 8，则创建一个异常
                    if (temp > 8) {
                        subscriber.onError(new Throwable("value >8"));
                        break;
                    } else {
                        subscriber.onNext(temp);
                    }
                    // 没有异常，正常结束
                    if (i == 4) {
                        subscriber.onCompleted();
                    }
                }
            }
        });

        integerObservable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                logger.info("{}", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                logger.info("error {}", e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                logger.info("onNext {}", integer);
            }
        });
    }
}
