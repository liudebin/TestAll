package qian.ling.yi.ext.rocketMQ.producer;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;

/**
 * Created by liuguobin on 2016/12/13.
 */
public class Producer {
        public static void main(String[] args) throws MQClientException, InterruptedException {
            // tc_pro1为Producer group name
            DefaultMQProducer producer = new DefaultMQProducer("tc_pro1"); // 手动指定Namesrv服务地址
            producer.setNamesrvAddr("localhost:9876");
            producer.setInstanceName("Producer1‐tp1");
//            producer.set
            producer.start();

            // 如果broker关闭了自动创建Topic功能，请手动添加Topic:tc_demo，以确保能正常发送消息
            for (int i = 0; i < 10; i++) {
                try {
                    Message msg = new Message("tc_demo",// topic
                            "TagA",// tag
                            ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)// body
                    );
                    SendResult sendResult = producer.send(msg);
                    LocalTransactionExecuter tranExecuter = new LocalTransactionExecuter() {
                        public LocalTransactionState executeLocalTransactionBranch(Message msg, Object arg) {
                                                                                   // TODO Auto‐generated method stub
                               return null;
                        }
                     };
                     //producer.sendMessageInTransaction(msg, tranExecuter, arg)
                    System.out.println(sendResult);
                } catch (Exception e) {
                    e.printStackTrace();
                    Thread.sleep(1000);
                }
            }
           producer.shutdown();
        }
}
