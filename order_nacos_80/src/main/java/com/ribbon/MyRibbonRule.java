package com.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

/**
 * 自定义ribbon的负载均衡策略
 */
public class MyRibbonRule extends AbstractLoadBalancerRule
{
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig){
    }

    @Override
    public Server choose(Object key)
    {
        return choose(getLoadBalancer(),key);
    }
    public Server choose(ILoadBalancer loadBalancer,Object key){
        return loadBalancer.getReachableServers().get(0);
    }
}
