package qian.ling.yi.base;

/**
 * 类的clinit方法测试基础类
 *
 * @author liuguobin
 * @date 2017/6/3
 */

public class ClassClInitBase {

    static{
        try {
            Thread.sleep(5000);
        } catch (Exception e) {

        }
        System.out.println("over = " + true);
    }

}
