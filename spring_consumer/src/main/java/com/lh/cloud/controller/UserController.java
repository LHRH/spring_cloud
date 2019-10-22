package com.lh.cloud.controller;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@Configuration
public class UserController {

    @Bean
    @LoadBalanced
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/get")
    @ResponseBody
    public String getUser() {
        System.out.println("2222");
        RestTemplate template = getRestTemplate();
        System.out.println("提交测试");
        return template.getForObject("http://spring-provider-service/get/110", String.class);
    }
}
