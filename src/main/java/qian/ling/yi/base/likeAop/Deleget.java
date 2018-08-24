package qian.ling.yi.base.likeAop;

/**
 * delegete
 *
 * @author liuguobin
 * @date 2018/8/3
 */

public class Deleget {
    Parent parent ;

    public void B() {
        System.out.println("d B begin");
        parent.B();
        System.out.println("d B end");
    }

    public static void main(String[] args) {
        Deleget d = new Deleget();
        d.parent = new Parent();
        d.B();
    }
}
