package com.yeyangshu.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息发送者，异步发送
 */
public class ProducerAsync {

    public static void main(String[] args) throws Exception {
        // producerGroup，分组
        DefaultMQProducer producer = new DefaultMQProducer("rocketMQGroup");
        // 设置nameserver地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        // 启动producer
        producer.start();

        // public Message(String topic, byte[] body) {}
        // topic：消息发送的地址
        // body：消息中的真实的数据
        Message message = new Message("myTopic01", "first message".getBytes());
        // 同步消息发送，需要等待回复确认
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("消息发送成功");
                System.out.println("sendResult：" + sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                // 如果发送异常，可以尝试重投
                // 或者调整业务逻辑
                System.out.println("发送异常");
            }
        });
    }
}