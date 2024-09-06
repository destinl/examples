package com.ls.jasypt_demo.domain.entity;

import com.ls.jasypt_demo.annotation.Sensitive;
import com.ls.jasypt_demo.domain.Enum.SensitiveStrategy;
import lombok.Data;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/6 22:59
 */
@Data
public class Person {
    /**
     * 真实姓名
     */
    @Sensitive(strategy = SensitiveStrategy.USERNAME)
    private String realName;
    /**
     * 地址
     */
    @Sensitive(strategy = SensitiveStrategy.ADDRESS)
    private String address;
    /**
     * 电话号码
     */
    @Sensitive(strategy = SensitiveStrategy.PHONE)
    private String phoneNumber;
    /**
     * 身份证号码
     */
    @Sensitive(strategy = SensitiveStrategy.ID_CARD)
    private String idCard;
}
