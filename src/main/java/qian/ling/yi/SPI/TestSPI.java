package qian.ling.yi.SPI;

/**
 * Service Provider InterFace
 *
 * @author liuguobin
 * @date 2017/10/27
 */

public interface TestSPI {
    default void sayHi() {
        System.out.println("你好！！！");
    }
    default void sayHello() {
        System.out.println("Hello");
    }
}
