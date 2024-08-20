package com.ls.learn.aspect;

import com.ls.learn.annotation.RepeatSubmit;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/20 21:41
 */
@Aspect
@Slf4j
@Component
public class RepeatSubmitAspect {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut(value = "@annotation(repeatSubmit)")
    public void pointCutNoRepeatSubmit(RepeatSubmit repeatSubmit){

    }

    @Around(value = "pointCutNoRepeatSubmit(repeatSubmit)", argNames = "joinPoint,repeatSubmit")
    public Object around(ProceedingJoinPoint joinPoint, RepeatSubmit repeatSubmit){
        //(现在6.1.11版本获取HttpServletRequest的方法）
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(servletRequestAttributes == null){
            return "错误";
        }
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder).getRequest();
//        (之前获取HttpServletRequest的方法）
        // 使用 request 进行后续操作
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String type = repeatSubmit.limitType().name();
        String key = ":repeat_submit:";
        String url = request.getRequestURI();
        String ipAddress = request.getRemoteAddr();
        if((type.equalsIgnoreCase(RepeatSubmit.Type.PARAM.name()))){
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            String className = method.getDeclaringClass().getName();
            key = key + String.format("%s-%s-%s-%s", ipAddress, url, className, method);
        }else{
            String requestToken = request.getHeader("token");
            if(StringUtils.isBlank(requestToken)){
                log.error("token不存在，非法请求！");
                return "token不存在，非法请求！";
            }
            key = key + String.format("%s-%s", requestToken, url);
        }
        key = DigestUtils.md5DigestAsHex(key.getBytes(StandardCharsets.UTF_8));
        if(Boolean.FALSE.equals(stringRedisTemplate.hasKey(key)) && stringRedisTemplate.opsForValue().setIfAbsent(key
                , "", repeatSubmit.lockTime(), TimeUnit.SECONDS)){
            try {
                return joinPoint.proceed();
            } catch (Throwable e) {
                log.error("处理异常，请重试！");
                return "处理异常，请重试！";
//                throw new RuntimeException(e);
            } finally {
                stringRedisTemplate.delete(key);
            }
        }else{
            log.error("请勿重复提交");
            return "请勿重复提交";
        }


    }

}
