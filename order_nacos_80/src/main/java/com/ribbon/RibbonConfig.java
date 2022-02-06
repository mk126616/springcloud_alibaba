package com.ribbon;

import com.ribbon.MyRibbonRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//自定义ribbon负载均衡策略，可以实现某个服务负载均衡规则特殊处理,此处的bean没有注入到ioc中（原理需要探究源码）
//ribbon的配置需要@ComponentScan不能扫描到的地方，否则一旦被注入到ioc中所有的服务调用都会使用此规则
@Configuration
public class RibbonConfig
{
    @Bean
    public IRule randomRule(){
        return  new MyRibbonRule();
    }
}
