package com.lh.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.Random;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //开启feign 功能
@EnableEurekaClient
public class FeignHyStrixApp {
    public static void main(String[] args) {
        SpringApplication.run(FeignHyStrixApp.class, args);
    }
}
