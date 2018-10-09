package qian.ling.yi.ext.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TODO
 * date: 2018/10/8.
 * @author: guobin.liu@holaverse.com
 */

public class Consumer {
    public static void main(String[] args) {
        //测试常规服务
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        System.out.println("consumer start");
        DemoService demoService = context.getBean(DemoService.class);
        System.out.println("consumer");
        System.out.println(demoService.getPermissions(1L));
    }
}
