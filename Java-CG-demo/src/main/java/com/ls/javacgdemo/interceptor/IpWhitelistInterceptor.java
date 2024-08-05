package com.ls.javacgdemo.interceptor;

import com.ls.javacgdemo.config.IpWhitelistProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/5 21:22
 */
@Component
public class IpWhitelistInterceptor implements HandlerInterceptor {

    @Autowired
    private IpWhitelistProperties ipWhitelistProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String remoteAddr = request.getRemoteAddr();
        if (ipWhitelistProperties.getAddresses().contains(remoteAddr)) {
            return true;
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return false;
        }
    }
}