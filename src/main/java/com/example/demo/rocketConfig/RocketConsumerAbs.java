package com.example.demo.rocketConfig;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @auther hcy
 * @create 2020-07-09 14:24
 * @Description 消息消费抽象类，只定义连接件方式。具体的消息处理逻辑，自己再去实现
 */
@Configuration
public abstract  class RocketConsumerAbs {
    Logger log = LoggerFactory.getLogger(RocketConsumerAbs.class);
    @Autowired
    private RocketConsumerConfig rocketConsumerConfig;

    /**
     * 开启消费者监听服务
     * @param topic
     * @param tag
     * @throws MQClientException
     */
    public void listener(String topic, String tag) throws MQClientException {
        log.info("开启" + topic + ":" + tag + "消费者-------------------"+rocketConsumerConfig.toString());
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketConsumerConfig.getGroupName());
        consumer.setNamesrvAddr(rocketConsumerConfig.getNamesrvAddr());
        consumer.subscribe(topic,tag);
        // 开启内部类实现监听
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                //调用下面的抽象方法，具体的消息处理业务由各个实现类自己决定
                try {
                    return RocketConsumerAbs.this.dealBody(msgs);
                } catch (UnsupportedEncodingException e) {
                    log.error("系统不支持utf-8，请检查系统,",msgs.toString());
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            }
        });
        consumer.start();
        log.info("rocketmq启动成功---------------------------------------");
    }

    /**
     * 处理body的业务
      */
    public abstract ConsumeConcurrentlyStatus dealBody(List<MessageExt> msgs) throws UnsupportedEncodingException;


}
