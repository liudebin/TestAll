package qian.ling.yi.ext.rocketMQ.consumer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;


/**
 *
 * 可以广播和集群消费并存，然而不知道怎么分的，广播的有所有的
 * 默认集群消费
 *
 * 重新启动一个消费者，其他的消费者会……
 * Created by liuguobin on 2016/12/13.
 */
public class Consumer4 {

    public static void main(String[] args) throws InterruptedException, MQClientException {
        // tc_con1为Consumer group name,如果broker关闭了自动订阅功能，请手动添加订阅tc_con1，以确保能正常接收消息
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("tc_con2");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET); // 手动指定Namesrv服务地址
        consumer.setNamesrvAddr("localhost:9876");
        consumer.setInstanceName("Consumber1‐tp5");
        consumer.subscribe("tc_demo", "*");
//        consumer.setConsumerGroup("123");
        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List msgs, ConsumeConcurrentlyContext context) {
                System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer Started.");
    }

}
