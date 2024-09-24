package com.ls.cas_demo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/14 21:59
 */
public class LoginFilter implements Filter {
    public static final String USER_INFO = "user";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        Object userInfo = request.getSession().getAttribute(USER_INFO);;
        //如果未登陆，则拒绝请求，转向登陆页面
        String requestUrl = request.getServletPath();
        //不是登陆页面、不是去登陆、不是登陆状态，去登录页面
        if (!"/toLogin".equals(requestUrl) &&!requestUrl.startsWith("/login") && null == userInfo) {
            request.getRequestDispatcher("/toLogin").forward(request, response);
            return;
        }
        filterChain.doFilter(request,servletResponse);
    }
    @Override
    public void destroy() {
    }
}

