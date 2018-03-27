package com.lh.cloud.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "spring-provider", fallback = UserServiceImpl.class)
public interface IUserService {

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    String get(@RequestParam(value = "id") String id);
}
