package qian.ling.yi.base.JVMCustom.JVM;

import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * vm
 *
 * @author liuguobin
 * @date 2018/5/3
 */

public class MemoryTest {

    private static int index = 1;
    private final static Object[] s = new Object[65552];

    public void call(){
        index++;
        call();
    }

    /**
     * 栈 溢出
     * java.lang.StackOverflowError
        Stack deep : 10777
     */
    @Test
    public void testStack() {
        MemoryTest mock = new MemoryTest();
        try {
            mock.call();
        }catch (Throwable e){
            System.out.println("Stack deep : "+index);
            e.printStackTrace();
        }
    }

    /**
     * 堆 溢出
     * java.lang.OutOfMemoryError: Java heap space
     */
    @Test
    public void testHeap() {
        List<byte[]> list = new ArrayList<byte[]>();
        int i = 0 ;
        boolean flag = true;
        while(flag){
            try {
                i++;
                list.add(new byte[1024*1024]);//每次增加一个1M大小的数组对象
            } catch (Throwable e) {
                e.printStackTrace();
                flag = false;
                System.out.println("count="+i);//记录运行的次数
            }
        }
    }

    /**
     * 方法区 溢出
     * java.lang.OutOfMemoryError: Metaspace
     * @param args
     */
    public static void main(String[] args) {
        URL url = null;
        javassist.ClassPool cp = javassist.ClassPool.getDefault();

        for (int i = 0; ; i++) {
            try {
                Class c = cp.makeClass("eu.plumbr.demo.Generated" + i).toClass();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
//    java /Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib-XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m -Xmx16m  qian.ling.yi.base.JVMCustom.JVM.MemoryTest


//
}
