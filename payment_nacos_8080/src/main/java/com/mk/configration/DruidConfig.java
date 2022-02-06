package com.mk.configration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig
{
    //创建数据库连接池
    @ConfigurationProperties(prefix = "spring.datasource")   //绑定配置文件中的参数
    @Bean
    public DataSource dataSource(){
        return new DruidDataSource();
    }
    //druid监控
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParam = new HashMap<String, String>();
        initParam.put("loginUsername","root");
        initParam.put("loginPassword","123456");
        initParam.put("allow","");
        initParam.put("deny","");
        registrationBean.setInitParameters(initParam);
        return registrationBean;
    }
    //druid过滤器
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        Map<String,String> initParam = new HashMap<String, String>();
        initParam.put("exclusions","*.js,*.css,*.html,/druid/*");
        filterRegistrationBean.setInitParameters(initParam);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }
}