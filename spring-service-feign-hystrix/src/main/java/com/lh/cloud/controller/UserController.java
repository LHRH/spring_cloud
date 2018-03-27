package com.lh.cloud.controller;

import com.lh.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable String id) {
        return userService.get(id);
    }
}
