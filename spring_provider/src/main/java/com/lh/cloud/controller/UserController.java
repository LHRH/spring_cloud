package com.lh.cloud.controller;

import com.lh.cloud.msg.Msg;
import com.lh.cloud.service.IUserService;
import com.lh.cloud.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Msg<UserVo> getUser(@PathVariable String id, HttpServletRequest request) {
        return userService.getUser(id, request);
    }
}
