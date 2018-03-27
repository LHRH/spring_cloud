package com.lh.cloud.service;

import com.lh.cloud.msg.Msg;
import com.lh.cloud.vo.UserVo;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {

    Msg<UserVo> getUser(String id,HttpServletRequest request);
}
