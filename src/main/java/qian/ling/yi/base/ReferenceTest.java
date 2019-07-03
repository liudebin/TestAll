package qian.ling.yi.base;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import qian.ling.yi.base.JVMCustom.ClassFile;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void testHahsMap() {
        Map a = new HashMap<String, Integer>();
        a.put("a", "1");
        Map b = a;
        b.put("b", 2);
        a.put("c", 3);
        System.out.println(JSON.toJSONString(a));
        System.out.println(JSON.toJSONString(b));
    }

}
