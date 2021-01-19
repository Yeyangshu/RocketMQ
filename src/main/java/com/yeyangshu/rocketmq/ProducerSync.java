package com.yeyangshu.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息发送者，同步发送
 */
public class ProducerSync {

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
        Message message1 = new Message("myTopic01", "first message".getBytes());
        Message message2 = new Message("myTopic01", "second message".getBytes());
        Message message3 = new Message("myTopic01", "third message".getBytes());
        // 同步消息发送，需要等待回复确认
        List<Message> list = new ArrayList<>();
        list.add(message1);
        list.add(message2);
        list.add(message3);
        SendResult sendResult = producer.send(list);
        System.out.println(sendResult);

        producer.shutdown();
    }
}