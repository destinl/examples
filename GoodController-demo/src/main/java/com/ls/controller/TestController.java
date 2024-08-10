package com.ls.controller;

import com.ls.domain.dto.TestDTO;
import com.ls.service.TestService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/2 21:11
 */
@RestController(value = "prettyTestController")
@RequestMapping("/pretty")
@Validated
public class TestController {

    private TestService testService;

    // cannot be cast to java.lang.String 问题
    @GetMapping(value = "/returnString", produces = "application/json; charset=UTF-8")
    public String returnString() {
        return "success";
    }

    @GetMapping("/{num}")
    public Integer detail(@PathVariable("num") @Min(1) @Max(20) Integer num) {
        return num * num;
    }

    @GetMapping("/getByEmail")
    public TestDTO getByAccount(@RequestParam @NotBlank @Email String email) {
        TestDTO testDTO = new TestDTO();
        testDTO.setEmail(email);
        return testDTO;
    }

    @PostMapping("/test-validation")
    public void testValidation(@RequestBody @Validated TestDTO testDTO) {
        this.testService.save(testDTO);
    }

    @Autowired
    public void setTestService(TestService prettyTestService) {
        this.testService = prettyTestService;
    }

    @GetMapping("/index")
    public Object index(Long id, String kw) {
        // 自定义响应header
        HttpHeaders headers = new HttpHeaders() ;
        headers.add("x-version", "1.0.0") ;
        ResponseEntity<Object> response = new ResponseEntity<Object>(
                String.format("【id = %d, kw = %s】", id, kw),
                headers,
                HttpStatus.ACCEPTED
//                HttpStatusCode.valueOf(200)
        );
        return response ;
    }
}