package com.lh.cloud.service;

import com.lh.cloud.vo.UserVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@FeignClient(value = "spring-provider")
public interface IUserService {

    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    String getUser(@RequestParam(value = "id") String id);
}
