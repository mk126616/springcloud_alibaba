package com.mk.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class FlowService
{
    @SentinelResource("method")
    public void method(){
        System.out.println("method()方法被调用");
    }
}
