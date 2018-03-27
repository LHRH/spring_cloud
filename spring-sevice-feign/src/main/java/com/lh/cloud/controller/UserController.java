package com.lh.cloud.controller;

import com.lh.cloud.service.IUserService;
import com.lh.cloud.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable String id) {
        return userService.getUser(id);
    }
}
