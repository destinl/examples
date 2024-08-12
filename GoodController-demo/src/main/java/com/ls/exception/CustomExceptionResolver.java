package com.ls.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 实现HandlerExceptionResolver接口来完成全局异常的处理
 * @Author: ls
 * @Date: 2024/8/12 21:04
 */
@Component
public class CustomExceptionResolver implements HandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        LOGGER.error("接口请求出现异常，请求地址：{}，错误信息：{}", request.getRequestURI(), e.getMessage());
        if(e instanceof RuntimeException){
            // 设置响应类型为json格式
            ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
            mv.addObject("code", 500);
            mv.addObject("msg", e.getMessage());
            return mv;
        } else {
            // 设置响应类型为错误页面
            ModelAndView mv = new ModelAndView();
            mv.addObject("message", e.getMessage());
            mv.setViewName("error");
            return mv;
        }
    }
}