package com.ls.ssm_exp.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1015:54
 */
@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
        // 初始化代码，可选实现
        System.out.println("Filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 过滤器逻辑在请求处理前执行
        System.out.println("Request received at filter");

        // 继续调用过滤器链，传递请求和响应
        filterChain.doFilter(servletRequest, servletResponse);

        // 过滤器逻辑在响应处理后执行
        System.out.println("Response leaving filter");
    }

    @Override
    public void destroy() {
//        Filter.super.destroy();
        // 销毁代码，可选实现
        System.out.println("Filter destroyed");
    }
}
