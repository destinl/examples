package com.ls.ssm_exp.utils;

import com.ls.ssm_exp.domain.entity.User;

import java.beans.PropertyEditorSupport;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/27 15:27
 */
public class UserPropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] dataStr = text.split(",");
        User user = new User();
        user.setNickname(dataStr[0]);
        user.setAge(Integer.valueOf(dataStr[1]));
        setValue(user);
    }
//    简单用法
//    UserPropertyEditor editor = new UserPropertyEditor() ;
//    editor.setAsText("12,张三") ;
//    User user = editor.getValue() ;

//      统一管理的用法
//      PropertyEditorManager.registerEditor(User.class, UserPropertyEditor.class);
//      PropertyEditor editor = PropertyEditorManager.findEditor(User.class) ;
//      // 注意NPE情况
//      editor.setAsText("12,张三") ;
}
