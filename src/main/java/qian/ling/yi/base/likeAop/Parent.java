package qian.ling.yi.base.likeAop;

/**
 * de
 *
 * @author liuguobin
 * @date 2018/8/3
 */

public class Parent {

    public void A() {
        System.out.println("p A");
    }

    public void B() {
        this.A();
        System.out.println("p B");
    }

}
