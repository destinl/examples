package com.ls.ssm_exp;

import com.ls.ossdemo.core.OssTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1018:12
 */

@SpringBootTest
class ApplicationTest {
    @Autowired
    private OssTemplate ossTemplate;

    @Test
    void contextLoads() {
        ossTemplate.createBucket("bucket-demo-3");
    }

}
