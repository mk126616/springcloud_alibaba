package com.mk.configration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config
{
    /**
     * rest请求便捷操作工具
     * @return
     */
    @Bean
    @LoadBalanced //赋予template负载均衡的能力（加此注解后在发送请求时会被拦截器拦截，
 //     然后通过http调用此服务的url）
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }

}
