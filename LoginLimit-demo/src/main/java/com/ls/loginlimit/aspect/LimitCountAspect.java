package com.ls.loginlimit.aspect;

import com.google.common.collect.ImmutableList;
import com.ls.loginlimit.annotation.LimitCount;
import com.ls.loginlimit.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/29 21:11
 */
@Slf4j
@Aspect
@Component
public class LimitCountAspect {
    private final RedisTemplate<String, Serializable> limitRedisTemplate;
    @Autowired
    public LimitCountAspect(RedisTemplate<String, Serializable> limitRedisTemplate) {
        this.limitRedisTemplate = limitRedisTemplate;
    }
    @Pointcut("@annotation(com.ls.loginlimit.annotation.LimitCount)")
    public void pointcut() {
        // do nothing
    }
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes())).getRequest();
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        LimitCount annotation = method.getAnnotation(LimitCount.class);
        //注解名称
        String name = annotation.name();
        //注解key
        String key = annotation.key();
        //访问IP
        String ip = IPUtil.getIpAddr(request);
        //过期时间
        int limitPeriod = annotation.period();
        //过期次数
        int limitCount = annotation.count();
        ImmutableList<String> keys = ImmutableList.of(StringUtils.join(annotation.prefix() + "_", key, ip));
        String luaScript = buildLuaScript();
        RedisScript<Number> redisScript = new DefaultRedisScript<>(luaScript, Number.class);
        Number count = limitRedisTemplate.execute(redisScript, keys, limitCount, limitPeriod);
        log.info("IP:{} 第 {} 次访问key为 {}，描述为 [{}] 的接口", ip, count, keys, name);
        if (count != null && count.intValue() <= limitCount) {
            return point.proceed();
        } else {
            return "接口访问超出频率限制";
        }
    }
    /**
     * 限流脚本
     * 调用的时候不超过阈值，则直接返回并执行计算器自加。
     *
     * @return lua脚本
     */
    private String buildLuaScript() {
        return "local c" +
                "\nc = redis.call('get',KEYS[1])" +
                "\nif c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\nreturn c;" +
                "\nend" +
                "\nc = redis.call('incr',KEYS[1])" +
                "\nif tonumber(c) == 1 then" +
                "\nredis.call('expire',KEYS[1],ARGV[2])" +
                "\nend" +
                "\nreturn c;";
    }
}