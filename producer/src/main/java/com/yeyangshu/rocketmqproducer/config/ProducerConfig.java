package com.yeyangshu.rocketmqproducer.config;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2021/1/23 21:57
 */
@Configuration
public class ProducerConfig {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProducerConfig.class);

    @Value("${rocketmq.producer.groupName}")
    private String groupName;

    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;

    @Bean
    public DefaultMQProducer getDefaultMQProducer() {
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr(namesrvAddr);
        try {
            producer.start();
            LOGGER.info(String.format("producer is start!, groupName:[%s], nameSrvAddr:[%s]", this.groupName, this.namesrvAddr));
        } catch (MQClientException e) {
            LOGGER.error(String.format("producer is error {}", e.getMessage(), e));
        }
        return producer;
    }
}
