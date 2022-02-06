package com.mk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderStarter_nacos_80
{
    public static void main(String[] args)

    {
        SpringApplication.run(OrderStarter_nacos_80.class, args);
    }
}
