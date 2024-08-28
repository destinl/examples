package com.ls.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/28 20:46
 */
public class CrossFilter implements Filter {
    /**
     * 允许跨域的白名单域名
     */
    private final static Set<String> ALLOW_DOMAINS = new HashSet<>();
    static {
        ALLOW_DOMAINS.add("http://127.0.0.1:8848");
    }
    @Override
    public void init(FilterConfig config) throws ServletException {}
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        // 获取客户端原始请求域
        String origin = request.getHeader("Origin");
        String originDomain = removeHttp(origin);
        if(ALLOW_DOMAINS.contains(originDomain)){
            // 在响应对象中，添加CROS协议相关的header属性
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,HEAD,PUT,PATCH");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Authorization,authorization");
            response.setHeader("Access-Control-Allow-Credentials","true");
        }
        //继续往下传递
        filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override
    public void destroy() {}
    /**
     * 移除http协议头部
     * @param url
     * @return
     */
    public static String removeHttp(String url){
        return url.replace("http://", "").replace("https://", "");
    }
}
