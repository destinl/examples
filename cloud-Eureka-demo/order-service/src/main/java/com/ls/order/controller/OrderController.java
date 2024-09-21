package com.ls.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/21 15:09
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    //    @Autowired
//    private OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/{orderId}")
    public String queryOrderByUserId(@PathVariable("orderId") Long orderId) {
//        // 根据id查询订单并返回
//        Order order = orderService.getById(orderId);
//        User user = restTemplate.getForObject("http://localhost:9001/user/" + order.getUserId(), User.class);
//        order.setUser(user);
        return restTemplate.getForObject("http://userservice/user/" + orderId, String.class);
    }
}