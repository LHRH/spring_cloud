package com.lh.cloud.service;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("spring-provider")
public interface HelloClient extends IHelloService {

}
