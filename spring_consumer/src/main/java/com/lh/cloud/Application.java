package com.lh.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class Application {

    public static void main(String[] args) {
        System.out.println("提交测试");
        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }
}
