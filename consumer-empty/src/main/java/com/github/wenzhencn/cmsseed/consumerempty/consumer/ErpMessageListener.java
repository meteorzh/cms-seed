package com.github.wenzhencn.cmsseed.consumerempty.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * ERP消息消费
 * @author wenzhen
 * @since : Created in 2019/11/4 13:36
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "${app.mq.topic-erp}", consumerGroup = "${app.mq.consumer-group-erp}")
public class ErpMessageListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info("消费消息: {}", message);
    }
}
