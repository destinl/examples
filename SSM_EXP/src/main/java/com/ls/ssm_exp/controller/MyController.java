package com.ls.ssm_exp.controller;

import com.ls.ssm_exp.domain.entity.User;
import com.ls.ssm_exp.domain.resp.UserResponse;
import com.ls.ssm_exp.service.MyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/2912:21
 */
@RestController
@RequestMapping("/")
public class MyController {

//    建议在编写代码时，最好能遵守一个比较好的规范，比如常见的SOLID规范。
//
//    SOLID 实际上是五个设计原则首字母的缩写，它们分别是：
//
//    单一职责原则（Single responsibility principle, SRP）
//    开放封闭原则（Open–closed principle, OCP）
//    Liskov 替换原则（Liskov substitution principle, LSP）
//    接口隔离原则（Interface segregation principle, ISP）
//    依赖倒置原则（Dependency inversion principle, DIP）

    @Autowired
    private MyService myService; // 假设您的服务类名为 MyService

    @GetMapping("test")
    public void someTransactionalOperation( String name){
        myService.someTransactionalOperation("Transactional");
    }

    @GetMapping("/user/{userId}")
    public void getUserById(@PathVariable String userId) {
        // 业务逻辑
    }

//    @PostMapping("/user/register")
//    public void getGradeById(@RequestBody User user) throws Exception {
//        // 代码逻辑
//        if(StringUtils.isBlank(user.getNickname())){
//            throw new Exception("Nickname is required.");
//        }
//    }

    @PostMapping("/user/register")
    public void getGradeById(@Validated @RequestBody User user) {
        // 代码逻辑
//        关于调用业务方法，这里的业务方法是写一个大而全的方法？还是需要按业务归类？
//
//        遵守一个原则：有强关联性的逻辑放在一个service方法内，没有强关联性的单令拎出来。
        //业务归类写法如下：
//        userService.register(user);
//        coupon.sendCoupon(userId);


        // 调用注册的业务方法
//        String userId = userService.regist(user);
//          组织返回数据
//        return new UserResponse(userId, user.getNickname);

//        try {
//            String userId = userService.regist(user);
//        } catch (Exception e) {
//            throw new CustomException();
//        }
//        return new UserResponse(userId, user.getNickname);



    }

    @RequestMapping("/test")
    public void example(HttpServletRequest request, HttpServletResponse response) {
        // 处理请求和响应
    }

    @RequestMapping("/user/{id}")
    public String getUser(@PathVariable("id") String userId) {
        // 使用 userId 进行处理
        return "userDetail";
    }

    @RequestMapping("/search")
    public String search(@RequestParam("query") String query) {
        // 使用 query 进行搜索
        return "searchResults";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestBody User user) {
        // 处理 user 对象
        return "user";
    }

    @RequestMapping("/register")
    public String register(@ModelAttribute User user) {
        // 处理 user 对象
        return "user";
    }

    @RequestMapping("/profile")
    public String profile(@SessionAttribute("user") User user) {
        // 处理会话中的 user 对象
        return "profile";
    }

    @RequestMapping("/headers")
    public String headers(@RequestHeader("User-Agent") String userAgent) {
        // 使用 userAgent 进行处理
        return "headerInfo";
    }

    @RequestMapping("/cookies")
    public String cookies(@CookieValue("sessionId") String sessionId) {
        // 使用 sessionId 进行处理
        return sessionId;
    }

//    自定义参数解析器
//    可以通过实现 HandlerMethodArgumentResolver接口来自定义参数解析逻辑。
//    @RequestMapping("/custom")
//    public String custom(CustomObject customObject) {
//        // 使用自定义对象进行处理
//        return "";
//    }

}