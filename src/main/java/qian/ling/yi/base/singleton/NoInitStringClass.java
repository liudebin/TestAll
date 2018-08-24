package qian.ling.yi.base.singleton;

/**
 * @author liuguobin
 * @date 2018/5/21
 */

public class NoInitStringClass {
    static {
        System.out.println("NoInitStringClass init");
    }

    public  final static String a = "aaaaaa"; // 已经放入 字符串常量池。
//    public final static int  b = 3000; // 。
    public final static Integer D = 11111;
    public final static SimpleClass  c = new SimpleClass(); // 。

}
