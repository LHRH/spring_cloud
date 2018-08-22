package com.service.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LH
 * @Description:
 * @Date: 2018/8/21 0021 17:05
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/hi")
    public String hi(){
        return "I'm forezp";
    }
}
