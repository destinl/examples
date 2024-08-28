package com.ls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/28 20:56
 */
@RestController("/to8848")
public class ControllerIn8080 {

//    public void makeRequestTo8848() {
//        try {
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder()
//                    .url("http://localhost:8848/yourControllerEndpointIn8848")
//                    .build();
//            Response response = client.newCall(request).execute();
//            // 处理响应
//            System.out.println(response.body().string());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @Autowired
    private RestTemplate restTemplate;

//    @CrossOrigin(origins = "http://127.0.0.1:8848", maxAge = 1800)
    @GetMapping("/makeRequestTo8848")
    public String  makeRequestTo8848() {
        String url = "http://127.0.0.1:8848/query8848";
        // 发起 GET 请求并获取响应结果
        String response = restTemplate.getForObject(url, String.class);
        // 处理响应
        System.out.println(response);
        return response;
    }
}
