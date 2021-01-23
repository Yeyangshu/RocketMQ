package com.yeyangshu.rocketmqproducer.controller;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2021/1/23 21:49
 */
@RestController
public class ProducerController {

    @Autowired
    DefaultMQProducer producer;

    @PostMapping("send")
    public Object send() throws Exception {
        Message message = new Message("topic", "HelloWorld".getBytes());
        return producer.send(message);
    }
}
