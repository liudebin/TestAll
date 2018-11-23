package qian.ling.yi.ext.spring.aop;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO
 *
 * @date: 2018/10/9.
 * @author: guobin.liu@holaverse.com
 */
@Component
public class TransactionBase {

    @Transactional
    public void test1() {
        System.out.println(this.getClass());
    }

    public void test2() {
        System.out.println(this.getClass() + "2");
        test1();
    }


}
