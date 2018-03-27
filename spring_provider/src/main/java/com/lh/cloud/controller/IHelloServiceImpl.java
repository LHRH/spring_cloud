package com.lh.cloud.controller;

import com.lh.cloud.service.IHelloService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IHelloServiceImpl implements IHelloService {

    @Override
    public String get(String id) {
        return "Hello Word " + id;
    }
}
