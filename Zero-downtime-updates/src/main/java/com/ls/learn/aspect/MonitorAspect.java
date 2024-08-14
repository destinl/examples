package com.ls.learn.aspect;

import com.ls.learn.annotation.Monitor;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/14 22:44
 */

@Component
@Aspect
public class MonitorAspect {

    private final MeterRegistry meterRegistry ;
    // 度量名称（你可以通过注解自定义）
    private static final String API_TIMER_METER_NAME = "myapp.api.timer" ;

    public MonitorAspect(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry ;
    }

    @Pointcut("@annotation(monitor)")
    private void pcMonitor(Monitor monitor) {} ;

    @Around("pcMonitor(monitor)")
    public Object around(ProceedingJoinPoint pjp, Monitor monitor) throws Throwable {
        Timer.Sample sample = Timer.start(this.meterRegistry) ;
        String[] tags = monitor.tags() ;

        Object ret = null ;
        Throwable ex = null ;

        try {
            ret = pjp.proceed() ;
        } catch (Throwable th) {
            ex = th ;
            throw th ;
        } finally {
            List<String> listTags = new ArrayList<>() ;
            listTags.addAll(Arrays.asList(tags)) ;
            // 出现异常也会将异常名称打入tag
            if (Objects.nonNull(ex)) {
                listTags.add(ex.getClass().getSimpleName()) ;
            }
            Timer timer = meterRegistry.timer(API_TIMER_METER_NAME, listTags.toArray(new String[0])) ;
            sample.stop(timer) ;
        }
        return ret ;
    }
}
