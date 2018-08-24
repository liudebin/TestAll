package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.base.JVMCustom.ClassFile;

/**
 * ReferenceTest
 *
 * 值传递
 * @author liuguobin
 * @date 2018/5/10
 */

public class ReferenceTest {
    int i = 0;
    static int j = 1;
    @Test
    public void testBase() {
        int i = 1;
        int j = i;
        i = 3;
        String c = "123231312";
        String d = c;
        c = "2";
        System.out.println(i);
        System.out.println(j);
    }

    public static void main(String[] args) {

        String a = "123231312";
        String b = a;
        a = "2";
        ClassFile classFile =  new ClassFile();
        System.out.println(a);
        System.out.println(b);
    }

}
