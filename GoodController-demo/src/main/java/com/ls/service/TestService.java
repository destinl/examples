package com.ls.service;

import com.ls.domain.dto.TestDTO;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/2 21:12
 */
@Service
public class TestService {

    public Double service(TestDTO testDTO) throws Exception {
        if (testDTO.getNum() <= 0) {
            throw new Exception("输入的数字需要大于0");
        }
        if (testDTO.getType().equals("square")) {
            return Math.pow(testDTO.getNum(), 2);
        }
        if (testDTO.getType().equals("factorial")) {
            double result = 1;
            int num = testDTO.getNum();
            while (num > 1) {
                result = result * num;
                num -= 1;
            }
            return result;
        }
        throw new Exception("未识别的算法");
    }

    public void save(TestDTO testDTO) {
    }
}
