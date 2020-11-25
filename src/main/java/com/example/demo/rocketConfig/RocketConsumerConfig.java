package com.example.demo.rocketConfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @auther hcy
 * @create 2020-07-09 14:23
 * @Description
 */
@ConfigurationProperties(prefix = "rocketmq.consumer")
@Configuration
@Data
public class RocketConsumerConfig {
    private String groupName;
    private String namesrvAddr;
}
