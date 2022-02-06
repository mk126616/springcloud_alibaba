package com.mk.controller;


import com.mk.configration.LoadBalancer;
import com.mk.entity.PaymentEntity;
import com.mk.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@EnableDiscoveryClient
@RestController
@Slf4j
public class OrderController implements ApplicationContextAware
{
    /**
     * 支付微服务的url
     */

    @Value("${server-url.payment-server}")
    private  String paymentService;

    @Autowired
    private RestTemplate restTemplate;

    private ApplicationContext context;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancer loadBalancer;

    /**
     * 创建订单
     *
     * @param paymentEntity
     * @return
     */
    @PostMapping("/consumer/payment")
    Result createPayment(PaymentEntity paymentEntity)
    {
        return restTemplate.postForObject(paymentService+"/payment",paymentEntity,Result.class);
    }
    /**
     * 所有订单
     *
     * @return
     */
    @GetMapping("/consumer/payments")
    Result getAllPayment()
    {
        return restTemplate.getForObject(paymentService+"/payments",Result.class);
    }
    /**
     * 所有订单
     *
     * @return
     */
    @GetMapping("/consumer/payments2")
    Result getAllPayment2()
    {
        ServiceInstance serviceInstance = null;
        try
        {
            //此处为@LoadBalanced注解赋予restTemplate负载均衡功能的原理
            List<ServiceInstance> instances = discoveryClient.getInstances("payment-service");
            serviceInstance = loadBalancer.choose(instances);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return restTemplate.getForObject(serviceInstance.getUri()+"/payments",Result.class);
    }



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.context = applicationContext;
    }
}
