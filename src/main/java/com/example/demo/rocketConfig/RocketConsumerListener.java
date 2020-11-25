package com.example.demo.rocketConfig;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * @auther hcy
 * @create 2020-07-09 14:58
 * @Description
 */
@Configuration
public class RocketConsumerListener extends RocketConsumerAbs implements ApplicationListener<ContextRefreshedEvent> {
    @Value("${rocketmq.consumer.topics}")
    private String[] topics;
    private Logger log = LoggerFactory.getLogger(RocketConsumerListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info(Arrays.toString( topics ));
        try {
            super.listener(topics[0], "*");
        } catch (MQClientException e) {
            log.error("rocket消费失败", e);
        }
    }

    @Override
    public ConsumeConcurrentlyStatus dealBody(List<MessageExt> msgs){
        try {
            for (MessageExt msg : msgs) {

                String msgStr = new String(msg.getBody(), "utf-8");
                System.out.println( msgStr );
                testJsonError(msgStr);

            }
        }catch (Exception e){
            System.out.println( "发生错误:" + e.toString());
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }


    public void testJsonError(String msg){
        JSONArray jar = JSONUtil.parseArray(msg);
        System.out.println( jar.toString() );
    }



}
