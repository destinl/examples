package com.ls.ssm_exp.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1015:32
 */
@Component
public class RateLimitInterceptor extends HandlerInterceptorAdapter {

    public static final int MAX_REQUESTS_PER_MINUTE = 100;
    public static final String RATE_LIMIT_KEY_PREFIX  = "rate_limit:";
    private final RedisTemplate<String, String> redisTemplate;

    public RateLimitInterceptor(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress =request.getRemoteAddr();
        String key = RATE_LIMIT_KEY_PREFIX  + ipAddress;
        long count = redisTemplate.opsForValue().increment(key, 1);
        if(count == 1){
            redisTemplate.expire(key, 1, TimeUnit.MINUTES);
        }
        if(count > MAX_REQUESTS_PER_MINUTE){
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("请求过于频繁，请稍后重试");
            return false;
        }
        return true;
    }

}