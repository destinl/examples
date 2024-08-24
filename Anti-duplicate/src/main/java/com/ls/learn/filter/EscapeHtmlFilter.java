package com.ls.learn.filter;

import com.ls.learn.utils.EscapeHtmlRequestWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/24 14:58
 */
@Component
@Order(1)
public class EscapeHtmlFilter implements Filter {
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)      throws IOException, ServletException {
        filterChain.doFilter(new EscapeHtmlRequestWrapper((HttpServletRequest) servletRequest), servletResponse);
    }
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
//            throws IOException, ServletException {
//        if (!isJsonRequest((HttpServletRequest) servletRequest)) {
//            filterChain.doFilter(new EscapeHtmlRequestWrapper((HttpServletRequest) servletRequest), servletResponse);
//        } else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }

    private boolean isJsonRequest(HttpServletRequest request) {
        String contentType = request.getContentType();
        return contentType!= null && contentType.contains("application/json");
    }
}
