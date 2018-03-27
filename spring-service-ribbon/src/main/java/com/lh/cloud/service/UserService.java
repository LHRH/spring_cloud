package com.lh.cloud.service;

import com.lh.cloud.msg.Msg;
import com.lh.cloud.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    RestTemplate template;

    public Msg<UserVo> getUser(String id) {
        return template.getForObject("http://spring-provider/getUser/" + id, Msg.class);
    }


}
