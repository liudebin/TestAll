package qian.ling.yi.base;

/**
 * 父类
 *
 * @author liuguobin
 * @date 2017/4/7
 */

public class SuperClass {
    static {
        System.out.println("SuperClass static init");
    }

    public int i =3;
    public SuperClass() {
        System.out.println("SuperClass init");
    }
    public static void houha() {
        System.out.println("houha");
    }
    public String str;

    public Long l;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Long getL() {
        return l;
    }

    public void setL(Long l) {
        this.l = l;
    }

    public void mul() {
        System.out.println("father = " );
    }
}
