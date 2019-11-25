package qian.ling.yi.ext.RxJava;

import org.junit.Test;
import qian.ling.yi.AbstractTest;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @date: 2019/8/29.
 * @author: guobin.liu@holaverse.com
 */

public class TTTT extends AbstractTest {

    @Test
    public void test(){
        Random random = new Random();

        ArrayList<Observable<Double>> list = new ArrayList<>();
        Observable<Double> doubleObservable = getDoubleObservable(random, 2);


    }

    private Observable<Double> getDoubleObservable(Random random, int n) {
        return Observable.range(0, n)
                    .map(i -> {
                        double x = random.nextDouble() * 2 - 1;
                        double y = random.nextDouble() * 2 - 1;
                        return (x * x + y * y) < 1 ? 1 : 0;
                    })
                    .reduce((j, k) -> j + k)
                    .map(l -> {
                        double v = 4.0 * l / n;
                        logger.info("{}", v);
                        return v;
                    })
                    .subscribeOn(Schedulers.computation());
    }
}
