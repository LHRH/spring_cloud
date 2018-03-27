package com.lh.cloud.controller;

import com.lh.cloud.msg.Msg;
import com.lh.cloud.service.UserService;
import com.lh.cloud.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUser/{id}")
    @ResponseBody
    public Msg<UserVo> get(@PathVariable String id) {
        return userService.getUser(id);
    }

}
