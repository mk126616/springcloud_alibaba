package com.mk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Nacos_config_client_8081
{
    public static void main(String[] args)
    {
        SpringApplication.run(Nacos_config_client_8081.class,args);
    }
}
