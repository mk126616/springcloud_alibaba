package com.mk.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.mk.entity.Result;
import com.mk.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("flowLimit")
@RestController
public class FlowLimitController
{
    @Autowired
    private FlowService service;

    @GetMapping("testA")
    public Result flowTestA()
    {
        service.method();
        return Result.success("flow testA");
    }



    @GetMapping("testB")
    public Result flowTestB(){
        return Result.success("flow testB");
    }


    @GetMapping("testC")
    public Result flowTestC() throws InterruptedException
    {
        Thread.sleep(1000);
        return Result.success("flow testC");
    }

    @GetMapping("testD")
    public Result flowTestD()
    {
        int age = 10/0;
        return Result.success("flow testD");
    }

    /**
     * 普通的限流
     * @return
     */
    @GetMapping("testE")
    @SentinelResource(value = "sentinelResource-testE",blockHandler = "blockHandler")
    public Result flowTestE()
    {
        return Result.success("flow testE");
    }


    public static Result blockHandler(BlockException e){
        return Result.success("blockHandler :flow testE");
    }

    /**
     * 热点限流
     * @param arg0
     * @return
     */
    @GetMapping("testF")
    @SentinelResource(value = "sentinelResource-testF",blockHandler = "blockHandlerF")
    public Result flowTestF(@RequestParam(value = "arg0",required = false) String arg0)
    {
        return Result.success("flow testF");
    }

    public static Result blockHandlerF(String arg0,BlockException e){
        return Result.success("blockHandler :flow testF");
    }
}
