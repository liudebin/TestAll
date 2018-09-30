package qian.ling.yi.object;

/**
 * Created by liuguobin on 2016/9/30.
 */
public class Testfuzhi {
    private String heh;
    private static String A;
    private final  String B ="B";
    private final static  String C ="C";
    private static final String D = "D";
//    private final static TestClass a = new TestClass();

    static abstract class Parent {}

    static class Girl extends Parent {}
    static class Son extends Parent {}

    public void tS(Parent p) {
        System.out.println("parent");
    }

    public void tS(Son son) {
        System.out.println("son");
    }

    public void tS(Girl girl) {
        System.out.println("girl");
    }


    public static void setA(String a) {
        A = a;
    }

    public static void main(String[] args) {
        System.out.println(A);
        Parent p = new Girl();
        Parent q = new Son();
        Testfuzhi testfuzhi = new Testfuzhi();
        testfuzhi.tS(p);
        testfuzhi.tS(q);

    }

    public void houhou(Long E) {
        Double dd = new Double(1);
    }



}
