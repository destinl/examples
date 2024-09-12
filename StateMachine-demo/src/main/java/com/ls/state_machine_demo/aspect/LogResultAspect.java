package com.ls.state_machine_demo.aspect;

import com.ls.state_machine_demo.annotation.LogResult;
import com.ls.state_machine_demo.domain.entity.Order;
import com.ls.state_machine_demo.enums.OrderStatus;
import com.ls.state_machine_demo.enums.OrderStatusChangeEvent;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.weaver.AjAttribute;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/12 22:50
 */
@Component
@Aspect
@Slf4j
public class LogResultAspect {
    //拦截 LogHistory注解
    @Pointcut("@annotation(com.ls.state_machine_demo.annotation.LogResult)")
    private void logResultPointCut() {
        //logResultPointCut 日志注解切点
    }
    @Resource
    private StateMachine<OrderStatus, OrderStatusChangeEvent> orderStateMachine;
    @Around("logResultPointCut()")
    public Object logResultAround(ProceedingJoinPoint pjp) throws Throwable {
        //获取参数
        Object[] args = pjp.getArgs();
        log.info("参数args:{}", args);
        Message message = (Message) args[0];
        Order order = (Order) message.getHeaders().get("order");
        //获取方法
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        // 获取LogHistory注解
        LogResult logResult = method.getAnnotation(LogResult.class);
        String key = logResult.key();
        Object returnVal = null;
        try {
            //执行方法
            returnVal = pjp.proceed();
            //如果业务执行正常，则保存信息
            //成功 则为1
            orderStateMachine.getExtendedState().getVariables().put(key + order.getId(), 1);
        } catch (Throwable e) {
            log.error("e:{}", e.getMessage());
            //如果业务执行异常，则保存信息
            //将异常信息变量信息中，失败则为0
            orderStateMachine.getExtendedState().getVariables().put(key + order.getId(), 0);
            throw e;
        }
        return returnVal;
    }
}
