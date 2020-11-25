package com.example.demo.rocketConfig;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther hcy
 * @create 2020-07-09 14:17
 * @Description
 */
@Configuration
public class RocketProducer {
    Logger log = LoggerFactory.getLogger(RocketProducer.class);

    @Autowired
    private RocketProducerConfig rocketProducerConfig;

    /**
     *  @ConditionalOnProperty 要求配置文件中必须有rocketmq.producer.namesrvAddr属性存在才能注入DefaultMQProducer对象。
     *  如果是要对具体值做限制，可以使用value+havingValue来指定
     * @return
     * @throws MQClientException
     */
    @Bean
    @ConditionalOnProperty(prefix = "rocketmq.producer", value = "namesrvAddr", matchIfMissing = false)
    public DefaultMQProducer defaultMQProducer() throws MQClientException {
        log.info("defaultProducer 正在创建---------------------------------------");
        DefaultMQProducer producer = new DefaultMQProducer(rocketProducerConfig.getGroupName());
        producer.setNamesrvAddr(rocketProducerConfig.getNamesrvAddr());
        producer.setVipChannelEnabled(false);
        producer.setRetryTimesWhenSendAsyncFailed(10);
        producer.start();
        log.info("rocketmq producer server开启成功---------------------------------.");
        return producer;
    }

}
