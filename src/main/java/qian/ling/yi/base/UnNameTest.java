package qian.ling.yi.base;

import rx.Producer;
import rx.Subscriber;

/**
 * unn
 *
 * @author liuguobin
 * @date 2018/3/29
 */

public class UnNameTest {
    public static void main(String[] args) {
        final String[] a = {"a"};
        Producer p = new Producer() {
            @Override
            public void request(long n) {
                System.out.println(1);
            }
        };

        Subscriber s = new Subscriber() {

            @Override
            public void onCompleted() {
                p.request(1);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(a[0]);
            }

            @Override
            public void onNext(Object o) {

            }
        };


        Producer p1 = new Producer() {
            @Override
            public void request(long n) {
                a[0] = "C";

                System.out.println(a[0]);
                System.out.println(s);
            }
        };
        System.out.println(s);
        p1.request(1);
        System.out.println(p1);

    }





}
