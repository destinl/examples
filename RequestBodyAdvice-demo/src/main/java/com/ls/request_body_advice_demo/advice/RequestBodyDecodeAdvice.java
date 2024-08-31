package com.ls.request_body_advice_demo.advice;

import com.ls.request_body_advice_demo.annotation.DecodeBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Base64;

/**
 * @Description: 通过 @RestControllerAdvice 注解，自动注册到 RequestMappingHandlerAdapter 中, @RestControllerAdvice 还可以通过其
 *               basePackages 和 basePackageClasses 属性更细粒度地控制要拦截的 Controller
 * @Author: ls
 * @Date: 2024/8/31 22:52
 */
@RestControllerAdvice
public class RequestBodyDecodeAdvice extends RequestBodyAdviceAdapter {
    static final Logger log = LoggerFactory.getLogger(RequestBodyDecodeAdvice.class);
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasParameterAnnotation(DecodeBody.class);
    }
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {

        // 读取完整的客户端请求体，也就是加密/编码后的数据
        byte[] payload = StreamUtils.copyToByteArray(inputMessage.getBody());

        log.info("加密 Payload：{}", new String(payload));

        // 解码为原始数据
        byte[] rawPayload = Base64.getDecoder().decode(payload);

        log.info("原始 Payload：{}", new String(rawPayload));

        // 返回 HttpInputMessage 匿名对象
        return new HttpInputMessage() {
            @Override
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }
            @Override
            public InputStream getBody() throws IOException {
                // 使用原始数据构建为 ByteArrayInputStream
                return new ByteArrayInputStream(rawPayload);
            }
        };
    }
}
