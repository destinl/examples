//package com.ls.request_body_advice_demo.resolver;
//
//import com.ls.request_body_advice_demo.annotation.UserInfo;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.core.MethodParameter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
///**
// * @Description: HandlerMethodArgumentResolver可以帮助我们在处理请求时将请求参数转换为方法参数
// * @Author: ls
// * @Date: 2024/8/31 23:13
// */
//@Component
//public class UserArgumentResolver implements HandlerMethodArgumentResolver {
//    public boolean supportsParameter(MethodParameter parameter) {
//        return parameter.hasParameterAnnotation(UserInfo.class);
//    }
//
//    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
//                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
//        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
////        String token = request.getHeader("token");
//        //业务方法
////        userService.getByToken(token);
//        return "222";
//    }
//}