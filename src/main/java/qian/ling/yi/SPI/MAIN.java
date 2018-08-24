package qian.ling.yi.SPI;

import java.util.ServiceLoader;

/**
 * MAIN - SPI
 * 需要在META-INF/services中创建一个名为qian.ling.yi.TestSPI的文件，其中的内容就为该实现类的全限定名：qian.ling.yi.TestSPIImp。
 *
 * @author liuguobin
 * @date 2017/10/27
 */

public class MAIN {
    ServiceLoader<TestSPI> serviceLoader = ServiceLoader.load(TestSPI.class);
    public static void main(String[] args) {
        MAIN main = new MAIN();
        main.serviceLoader.iterator().forEachRemaining(TestSPI::sayHi);
    }
}
