package com.ls.ssm_exp.controller;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ls.ssm_exp.Application;
import com.ls.ssm_exp.domain.entity.User;
import com.ls.ssm_exp.service.MyService;

//import com.alibaba.fastjson.
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import javax.annotation.Resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/318:51
 */
//@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WebMvcTest(controllers =MyController.class)
//@RunWith(SpringRunner.class)
//SpringBoot1.4版本之前用的是@SpringApplicationConfiguration(classes = Application.class)
//@SpringBootTest(classes = Application.class)
//测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的
//@WebAppConfiguration
public class MyControllerTest {

//    @Autowired
//    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

//    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MyService myService; // 用户业务的bean


    private MockMvc mockMvc;

    //不行这个在具体test方法中用mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();也会报415
    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new MyController()).build();
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

//    @Before
//    public void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//    @Before
//    public void setup() {
//        // 实例化方式一
//        mockMvc = MockMvcBuilders.standaloneSetup(new MyController()).build();
//        // 实例化方式二
////		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }

//    @Test
//    public void whenReceiveHttpRqe_thenReturns200() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        String jsonBody = "{\"username\":\"test\", \"password\":\"testPassword\"}";
//        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonBody))
//                .andExpect(status().isOk());
//    }

//    @Test
//    public void whenValidInput_thenReturns200() throws Exception {
//        User user = new User("zhangsan", 21);
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        mockMvc.perform(MockMvcRequestBuilders.post("/user/register", 1111) // 模拟通过 @PathVariable传递参数
//                        .contentType("application/json")
////                        .param("name", "张三")) // 模拟通过 @RequestParam传递参数
//                        .content(objectMapper.writeValueAsString(user))) // 模拟通过 @RequestBody传递参数
//                .andExpect(status().isOk());
//    }

    @Test
    public void test_RequestHeader() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/headers")
                .header("token", "abc")
                .param("id", "123"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    public void test_PathVariable() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{userId}", 111))
//                        .header("token", "abc")
//                        .param("id", "123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    public void test_RequestParam() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/search")
                        .param("query", "张三")) // 模拟通过 @RequestParam传递参数
//                        .header("token", "abc")
//                        .param("id", "123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    public void test_RequestBody() throws Exception {
        User user = new User("zhangsan", 21);
//        objectMapper = new ObjectMapper();
        String jsonStr= JSON.toJSONString(user);
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/create")
                        .header("Content-Type","application/json")
                        .content(jsonStr))
//                        .contentType("application/json")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
                .andExpectAll(MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.content().contentType("application/json;" +
                        "charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    //测试校验请求参数:返回 400
    @Test
    void whenNicknameNull_thenReturns400() throws Exception {
        objectMapper = new ObjectMapper();
        User user = new User("", 21); // 当 nickname为空时，会抛出 400的 BadRequest异常
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/create")
                        .header("Content-Type","application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    //测试校验请求参数:返回 200
    @Test
    void testReturns200() throws Exception {
        objectMapper = new ObjectMapper();
        User user = new User("zhangsan", 21); // 当 nickname为空时，会抛出 400的 BadRequest异常
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/create")
                        .header("Content-Type","application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    //测试调用业务方法
//    对于业务方法调用的测试，通常我们会使用Mockito.mock来模拟业务方法的返回值，而业务方式的真实运行逻辑会在 Server的单元测试中完成
//    @Test
//    void whenLogic_thenReturnsExceptData() throws Exception {
//        User user = new User(null, 21);
//        // mock userService.register(user)返回值为 userId
//        Mockito.when(userService.register(user)).thenReturn("userId");
//
//        mockMvc.perform(post("/user/register")
//                        .content(objectMapper.writeValueAsString(user)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data", is("userId")));
//    }


//    测试组织返回数据
//    @Test
//    void whenLogic_thenReturnsExceptData() throws Exception {
//        User user = new User("zhangsan", 21);
//        // mock userService.register(user)返回值为 userId
//        Mockito.when(userService.register(user)).thenReturn("userId");
//
//        MvcResult mvcResult = mockMvc.perform(post("/user/register")
//                        .content(objectMapper.writeValueAsString(user)))
//                .andReturn(); //将结果返回
//        String expectedResponse = "userId";
//        String actualResponseBody = mvcResult.getResponse().getContentAsString();
//        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
//                objectMapper.writeValueAsString(expectedResponse));
//    }


//    测试统一异常处理
//    @Test
//    void whenNullValue_thenReturns400AndErrorResult() throws Exception {
//        User user = new User("zhangsan", 21);
//
//        MvcResult mvcResult = mockMvc.perform(post("/user/register")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(user)))
//                .andExpect(status().isBadRequest())
//                .andReturn();
//
//        ErrorResult expectedErrorResponse = new ErrorResult("Nickname", "Nickname is required.");
//        String actualResponse = mvcResult.getResponse().getContentAsString();
//        String expectedResponseBody = objectMapper.writeValueAsString(expectedErrorResponse);
//        assertThat(actualResponse).isEqualToIgnoringWhitespace(expectedErrorResponse);
//    }
}