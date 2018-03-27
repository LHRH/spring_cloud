package com.lh.cloud.service.impl;

import com.lh.cloud.msg.Msg;
import com.lh.cloud.service.IUserService;
import com.lh.cloud.tool.Http;
import com.lh.cloud.vo.UserVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component("userImpl")
public class UserImpl implements IUserService {


    @Value("${server.port}")
    String prot;

    @Override
    public Msg<UserVo> getUser(String id,HttpServletRequest request) {
        HttpServletRequest request2 = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = Http.getIpAddr(request);
        String ipAddr = Http.getIpAddr(request2);
        Msg<UserVo> msg = new Msg<UserVo>();
        msg.setData(new UserVo(ip, request.getRequestURL().toString(), prot, 100,request.getRemoteAddr()));
        return msg;
    }
}
