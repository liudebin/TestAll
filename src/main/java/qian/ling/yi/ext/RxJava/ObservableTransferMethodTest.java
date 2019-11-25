package qian.ling.yi.ext.RxJava;

import org.junit.Test;
import qian.ling.yi.AbstractTest;
import rx.Observable;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * TODO
 *
 * @date: 2019/8/29.
 * @author: guobin.liu@holaverse.com
 */

public class ObservableTransferMethodTest extends AbstractTest {

    @Test
    public void testBuffer(){
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8 , 9)
                .buffer(2)
                .subscribe(i -> logger.info("{}", i));
    }

    @Test
    public void testBufferSkip(){
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8 , 9)
                .buffer(2, 1)
                .subscribe(i -> logger.info("{}", i));
    }

    @Test
    public void ttt(){
//        Arrays.asList().stream().collect(groupby)
    }



}
