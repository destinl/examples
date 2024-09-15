package com.ls.jwt_demo.controller;

import com.ls.jwt_demo.annotation.JwtIgnore;
import com.ls.jwt_demo.domain.UserToken;
import com.ls.jwt_demo.domain.dto.UserDto;
import com.ls.jwt_demo.util.JwtTokenUtil;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/15 23:07
 */
@RestController
public class LoginController {
    /**
     * 登录
     * @param userDto
     * @return
     */
    @JwtIgnore
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String login(@RequestBody UserDto userDto, HttpServletResponse response){
//        //...参数合法性验证
//        //从数据库获取用户信息
//        User dbUser = userService.selectByUserNo(userDto.getUserNo);
//        //....用户、密码验证
//        //创建token，并将token放在响应头
        UserToken userToken = new UserToken().setUserName(userDto.getUserEmail()).setUserNo(userDto.getUserPwd());
//        BeanUtils.copyProperties(dbUser,userToken);
        String token = JwtTokenUtil.createToken(JSONObject.toJSONString(userToken));
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, token);
//        //定义返回结果
//        UserVo result = new UserVo();
//        BeanUtils.copyProperties(dbUser,result);
        return "ok";
    }
}
