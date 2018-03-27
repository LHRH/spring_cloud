package com.lh.zuul.config;

import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: LH
 * @Description: 自定义路由规则
 * @Date: 2018/3/22 0022 16:24
 */
@Configuration
public class RouteConfig {

    /**
     * 访问网关/xxx/**. 将会被路由到spring-service-xxx 服务进行处理
     * @return
     */
    @Bean
    public PatternServiceRouteMapper patternServiceRouteMapper(){
        return new PatternServiceRouteMapper("(spring)-(service)-(?<module>.+)","${module}/**");
    }
}
