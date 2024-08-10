package com.ls.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/10 15:59
 */

@Component
public class ExceptionResolverConfig implements WebMvcConfigurer {

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.remove(resolvers.size() - 1) ;
        resolvers.add(new DefaultHandlerExceptionResolver() {
            @Override
            protected ModelAndView handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request,
                                                                 HttpServletResponse response, Object handler) throws IOException {
                ModelAndView mv = new ModelAndView(new View() {
                    @Override
                    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
                        // 这里实现自己的逻辑
                        response.getWriter().println(ex.getMessage()) ;
                    }
                }) ;
                return mv ;
            }
        }) ;
    }
}
