package com.lh.cloud.controller;

import com.lh.cloud.service.HelloClient;
import com.lh.cloud.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloClient helloClient;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String get(@PathVariable String id) {
        return helloClient.get(id);
    }
}
