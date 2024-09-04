package com.ls.interface_limit_demo.aspect;

import com.ls.interface_limit_demo.annotation.RequestLock;
import com.ls.interface_limit_demo.util.RequestKeyGenerator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/3 22:44
 */
@Aspect
@Configuration
@Order(2)
public class RedissonRequestLockAspect {

    private RedissonClient redissonClient;

    @Autowired
    public RedissonRequestLockAspect(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Around("execution(public * * (..)) && @annotation(com.ls.interface_limit_demo.annotation.RequestLock)")
    public Object interceptor(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RequestLock requestLock = method.getAnnotation(RequestLock.class);
        if (StringUtils.isEmpty(requestLock.prefix())) {
            throw new RuntimeException("重复提交前缀不能为空");
//            throw new BizException(ResponseCodeEnum.BIZ_CHECK_FAIL, "重复提交前缀不能为空");
        }
        //获取自定义key
        final String lockKey = RequestKeyGenerator.getLockKey(joinPoint);
        // 使用Redisson分布式锁的方式判断是否重复提交
        RLock lock = redissonClient.getLock(lockKey);
        boolean isLocked = false;
        try {
            //尝试抢占锁
            isLocked = lock.tryLock();
            //没有拿到锁说明已经有了请求了
            if (!isLocked) {
                throw new RuntimeException("您的操作太快了,请稍后重试");
//                throw new BizException(ResponseCodeEnum.BIZ_CHECK_FAIL, "您的操作太快了,请稍后重试");
            }
            //拿到锁后设置过期时间
            lock.lock(requestLock.expire(), requestLock.timeUnit());
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException("系统异常");
//                throw new BizException(ResponseCodeEnum.BIZ_CHECK_FAIL, "系统异常");
            }
        } catch (Exception e) {
            throw new RuntimeException("您的操作太快了,请稍后重试");
//            throw new BizException(ResponseCodeEnum.BIZ_CHECK_FAIL, "您的操作太快了,请稍后重试");
        } finally {
            //释放锁
            if (isLocked && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

    }
}
