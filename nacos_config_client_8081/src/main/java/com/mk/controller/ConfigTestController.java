package com.mk.controller;

import com.mk.entity.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope //配置更新后客户端动态更新
@RestController
public class ConfigTestController
{
    @Value("${config.key}")
    private String configKey;
    @Value("${config.version}")
    private String configVersion;

    @GetMapping("config")
    public Result getNacosConfig(){
        return Result.success("配置key为："+configKey+",配置版本为："+configVersion);
    }
}
