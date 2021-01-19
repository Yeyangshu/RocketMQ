package com.yeyangshu.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 消息消费者
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("rocketMQGroup");
        consumer.setNamesrvAddr("127.0.0.1:9876");

        // 每个consumer只能关注一个topic
        // subscribe(final String topic, final String subExpression)
        // 过滤器，"*"表示不过滤
        consumer.subscribe("myTopic01", "*");
        // registerMessageListener(final MessageListenerConcurrently messageListener)
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt message : msgs) {
                    System.out.println(new String(message.getBody()));
                }
                // 默认情况下点对点，这条消息只会被一个consumer消费到
                // ack
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer start...");
    }
}