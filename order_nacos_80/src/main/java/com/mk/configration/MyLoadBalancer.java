package com.mk.configration;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLoadBalancer implements LoadBalancer
{
    /**
     * 记录当前请求次数
     * AtomicInteger只能保证高并发下修改数据时的原子性，业务代码一旦有问题也会导致线程安全问题。
     * 比如以下业务AtomicInteger值需要依次递增，但是业务代码中两个线程如果同时拿到了0，都增加1，增加后正确的值应该为2，
     * 调用修改方法时两个线程都给的值为1，则最终的值为1，数据出现问题。
     * 因此在此业务场景下可以对整个方法加锁，或者使用轻量级别的自旋锁
     */
    private  AtomicInteger requestCount = new AtomicInteger(0);

    @Override
    public ServiceInstance choose(List<ServiceInstance> serviceInstances) throws Exception
    {
        int current;int next;int serverCount = serviceInstances.size();
        if(serviceInstances == null || serviceInstances.size() == 0){
            throw new Exception("服务不存在");
        }
        //每次请求完成需要维护请求次数值，但是高并发情况下值可能会被别的线程修改因此使用自旋锁（比synchronized轻量级，
        // 如果使用synchronized实现的话需要锁整个方法效率低）,如果不考虑需要将nextcurrent重置的话则可以直接用getAndIncrement()方法代替
        do {
            current = requestCount.get();
            next = current == Integer.MAX_VALUE ? 0 : current+1;
            //compareAndSet 和乐观锁比较像，如果当前值为期望值current 则更新值为next，否则返回false
        }while (!requestCount.compareAndSet(current,next));
        //此处的策略为轮询访问服务
        return serviceInstances.get(next % serverCount);
    }
}
