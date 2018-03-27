package com.lh.cloud.service.impl;

import com.lh.cloud.enums.EnErrorCode;
import com.lh.cloud.msg.Msg;
import com.lh.cloud.service.IUserService;
import com.lh.cloud.vo.UserVo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Component("userServiceImpl")
public class UserServiceImpl implements IUserService {

    @Autowired
    RestTemplate template;

    @HystrixCommand(fallbackMethod = "hiError")
    @Override
    public String getUser(String id) {
        return template.getForObject("http://spring-provider/get/" + id, String.class);
    }

    public String hiError(String id) {
        Msg msg = new Msg();
        msg.setResult(EnErrorCode.INVALID);
        msg.setData(id);
        return msg.toJson();
    }
}
