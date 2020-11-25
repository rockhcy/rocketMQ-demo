package com.example.demo.rocketConfig;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @auther hcy
 * @create 2020-07-09 13:55
 * @Description
 */
@ConfigurationProperties(prefix = "rocketmq.producer")
@Configuration
@Data
public class RocketProducerConfig {

    private String namesrvAddr;
    private String groupName;



}
