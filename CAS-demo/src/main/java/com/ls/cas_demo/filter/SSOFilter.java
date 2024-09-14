package com.ls.cas_demo.filter;

import com.ls.cas_demo.domain.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/14 22:20
 */
public class SSOFilter implements Filter {
    private RedisTemplate redisTemplate;
    public static final String USER_INFO = "user";
    public SSOFilter(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }
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
        //不是登陆页面、不是去登陆、不是登陆状态，都去登录页面
        if (!"/toLogin".equals(requestUrl) &&!requestUrl.startsWith("/login") && null == userInfo) {
            String ticket = request.getParameter("ticket");
            //有票据,则使用票据去尝试拿取用户信息
            if (null != ticket){
                userInfo = redisTemplate.opsForValue().get(ticket);
            }
            //无法得到用户信息，则去登陆页面
            if (null == userInfo){
                response.sendRedirect("http://127.0.0.1:8080/toLogin?url="+request.getRequestURL().toString());
                return ;
            }
            /**
             * 将用户信息，加载进session中
             */
            User user = (User) userInfo;
            request.getSession().setAttribute(SSOFilter.USER_INFO,user);
            redisTemplate.delete(ticket);
        }
        filterChain.doFilter(request,servletResponse);
    }
    @Override
    public void destroy() {
    }
}

