package com.ls.learn.controller;

import com.ls.learn.domain.entity.Article;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; /**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/24 14:56
 */
// 接口
@RestController
@RequestMapping("/modifybody")
public class ModifyBodyController {
    @PostMapping
    public Article save(@RequestBody Article article) {
        return article ;
    }

    @PostMapping("/test")
    public String testRequest(@RequestBody String article){
        return "ok";
    }
}
