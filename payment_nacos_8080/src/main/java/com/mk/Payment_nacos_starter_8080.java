package com.mk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan(basePackages = "com.mk.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class Payment_nacos_starter_8080
{
    public static void main(String[] args)
    {
        SpringApplication.run(Payment_nacos_starter_8080.class,args);
    }
}
