package qian.ling.yi.base;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.Random;

/**
 * 生成随机数的方法
 * Created by liuguobin on 2016/11/3.
 */
public class RandomTest extends AbstractTest {

    /**
     * Random 多次重新启动可能会生成相同的值kk
     * Random 不设种子的话，不会重复，
     * Math.random() 就是通过这个
     */
    @Test
    public void test() {
        Random random1 = new Random(100); //设seed 都重复
        Random random2 = new Random(100);

        logger.info("random1的随机数：{}", random1.nextInt());
        logger.info("random2的随机数：{}", random2.nextInt());
        logger.info("random1的随机数：{}", random1.nextInt());
        logger.info("random2的随机数：{}", random2.nextInt());

        logger.info("random1的随机数：{}", random1.nextInt(10));
        logger.info("random2的随机数：{}", random2.nextInt(10));
        logger.info("random1的随机数：{}", random1.nextInt(10));
        logger.info("random2的随机数：{}", random2.nextInt(10));


        logger.info("{}", Math.random());
        logger.info("{}", Math.random());

        Random random3 = new Random();
        Random random4 = new Random();
        logger.info("{}", random3.nextInt(10));
        logger.info("{}", random4.nextInt(10));


    }
}
