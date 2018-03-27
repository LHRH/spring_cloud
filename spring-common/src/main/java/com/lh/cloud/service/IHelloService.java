package com.lh.cloud.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface IHelloService {

    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
    String get(@PathVariable("id") String id);
}
