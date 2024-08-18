package com.ls.learn.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.URLEncoder;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/12 21:32
 */
@RestController()
@RequestMapping("port/test")
public class TestPortController {
    @GetMapping("test")
    public String test() {
        return "1";
    }

    // 这里我们展示PPT文档
    @GetMapping("/{id}")
    public void stream(@PathVariable Long id, HttpServletResponse response) throws Exception {
        FileSystemResource resource = new FileSystemResource(new File("D:\\Chromedownloads\\documents\\1.pptx")) ;
        response.addHeader("Cache-Control", "public, max-age=86400") ;
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.addHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("1.pptx", "UTF-8")) ;
        StreamUtils.copy(resource.getInputStream(), response.getOutputStream()) ;
    }
}
