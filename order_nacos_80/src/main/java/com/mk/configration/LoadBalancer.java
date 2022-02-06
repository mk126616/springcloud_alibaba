package com.mk.configration;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer
{
    ServiceInstance choose(List<ServiceInstance> serviceInstances) throws Exception;
}
