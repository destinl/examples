package com.ls.caffeinedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;

import java.util.concurrent.Executors;

@EnableCaching
@SpringBootApplication
public class CaffeineDemoApplication {

//    @Bean
//    public RouterFunction<ServerResponse> routeFuction(){
//        return route()
//                .GET("/getUserName", request -> ServerResponse.ok().body("zhouyu"))
//                .GET("/getUserAge", request -> ServerResponse.ok().body("88"))
//                .build();
//    }

    //Springboot（虚拟线程）
    //Executors.newVirtualThreadPerTaskExecutor()在 Java 19 中引入，Java 17 中没有这个方法。
    //在 Java 17 中，如果想要实现类似的功能，无法直接使用这个特定的虚拟线程创建方法，
    //可能需要依赖一些第三方库或者自行实现基于 Java 17 已有线程机制的复杂线程管理逻辑来模拟类似的效果。
//    @Bean
//    public TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
//        return protocolHandler -> {
//            protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
//        };
//    }
    public static void main(String[] args) {
        SpringApplication.run(CaffeineDemoApplication.class, args);
    }

}
