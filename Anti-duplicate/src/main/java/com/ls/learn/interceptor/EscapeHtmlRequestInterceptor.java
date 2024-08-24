package com.ls.learn.interceptor;

import com.ls.learn.utils.EscapeHtmlRequestWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/24 17:02
 */
public class EscapeHtmlRequestInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)      throws Exception {
        EscapeHtmlRequestWrapper htmlEscapeRequestWrapper = new EscapeHtmlRequestWrapper(request);
        // 其实写到这你就应该想到这里的EscapeHtmlRequestWrapper 是如何传递下去的呢？    
        //  这里的返回值仅仅是boolean类型是否要继续请求而已。    
        //  这里我们姑且像下面这样调用父类的方法吧
        return HandlerInterceptor.super.preHandle(htmlEscapeRequestWrapper, response, handler) ;
    }
}
