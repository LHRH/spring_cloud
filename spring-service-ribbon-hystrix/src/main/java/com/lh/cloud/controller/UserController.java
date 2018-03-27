package com.lh.cloud.controller;

import com.lh.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/get/{id}")
    public String get(@PathVariable String id) {
        return userService.getUser(id);
    }

}
