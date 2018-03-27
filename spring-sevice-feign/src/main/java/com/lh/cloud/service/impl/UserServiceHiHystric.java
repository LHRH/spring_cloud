package com.lh.cloud.service.impl;

import com.lh.cloud.service.IUserService;

/**
 * @Author: LH
 * @Description: 断路器
 * @Date: 2018/3/22 0022 14:41
 */

public class UserServiceHiHystric implements IUserService {
    @Override
    public String getUser(String id) {
        return "sorry" + id;
    }
}
