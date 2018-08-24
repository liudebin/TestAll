package qian.ling.yi.SPI.imp;

import qian.ling.yi.SPI.TestSPI;

/**
 * Service Provider Interface Impl
 *
 * @author liuguobin
 * @date 2017/10/27
 */

public class TestSPIImp implements TestSPI {
    @Override
    public void sayHi() {
        System.out.println("你好 ，我是 TestSPIImp");
    }
}
