package com.ls.ssm_exp.domain.entity;

import java.beans.PropertyEditorSupport;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/27 15:41
 */
public class UserEditor extends PropertyEditorSupport {
    //2最为简单，你不需要任何的注册动作，你只需要将该自定义的PropertyEditor，放到你要转换类型下的同包下即可
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] dataStr = text.split(",");
        User user = new User();
        user.setNickname(dataStr[0]);
        user.setAge(Integer.valueOf(dataStr[1]));
        setValue(user);
    }
}
