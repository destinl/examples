package com.ls.learn.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ls.learn.domain.entity.Article;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.util.HtmlUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class EscapeHtmlRequestWrapper extends HttpServletRequestWrapper {
    private String body = null;

    public EscapeHtmlRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
//        ServletInputStream inputStream = request.getInputStream();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        byte[] buffer = new byte[1024];
//        int len = 0;
//        while ((len = inputStream.read(buffer)) > -1) {
//            baos.write(buffer, 0, len);
//        }
//        String requestBody = new String(baos.toByteArray(), StandardCharsets.UTF_8);
        this.body = HtmlUtils.htmlEscape(String.valueOf(request));
//        // 假设是对 POST 请求的请求体进行转义
//        if ("POST".equals(request.getMethod())) {
//            ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(request);
//            byte[] buffer = inputMessage.getBody().readAllBytes();
//            String originalBody = new String(buffer, StandardCharsets.UTF_8);
//            // 进行 HTML 转义
//            this.body = HtmlUtils.htmlEscape(originalBody);
//        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (body!= null) {
            return new ServletInputStream() {
                @Override
                public boolean isFinished() {
                    return false;
                }

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setReadListener(ReadListener readListener) {

                }

                private final ByteArrayInputStream bais = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));

                @Override
                public int read() throws IOException {
                    return bais.read();
                }
            };
        } else {
            return super.getInputStream();
        }
    }

    @Override
    public BufferedReader getReader() throws IOException {
        if (body!= null) {
            return new BufferedReader(new InputStreamReader(getInputStream()));
        } else {
            return super.getReader();
        }
    }


//    @Override
//    public ServletInputStream getInputStream() throws IOException {
//        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
//        return new ServletInputStream() {
//            @Override
//            public boolean isFinished() {
//                return false;
//            }
//
//            @Override
//            public boolean isReady() {
//                return false;
//            }
//
//            @Override
//            public void setReadListener(ReadListener readListener) {
//
//            }
//
//            @Override
//            public int read() throws IOException {
//                return byteArrayInputStream.read();
//            }
//
//            // 这里还有其它的方法，默认即可
//        };
//    }
//
//    @Override
//    public BufferedReader getReader() {
//        try {
//            return new BufferedReader(new InputStreamReader(this.getInputStream()));
//        } catch (IOException e) {
//        }
//        return null;
//    }
}