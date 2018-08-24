package qian.ling.yi.base.likeAop;

/**
 * sun
 *
 * @author liuguobin
 * @date 2018/8/3
 */

public class Sun extends Parent {


    public void A() {
        System.out.println("s A begin");
        super.A();
        System.out.println("s A end");
    }

    public void B() {
        System.out.println("s B begin");
        super.B();
        System.out.println("s B end");
    }

    public static void main(String[] args) {
        new Sun().A();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        new Sun().B();
    }
}
