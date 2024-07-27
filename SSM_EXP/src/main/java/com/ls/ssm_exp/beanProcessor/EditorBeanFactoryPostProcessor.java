package com.ls.ssm_exp.beanProcessor;

import com.ls.ssm_exp.domain.entity.User;
import com.ls.ssm_exp.utils.UserPropertyEditor;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.validation.DataBinder;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/27 15:34
 */
public class EditorBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        // 注册到BeanFactory中
        configurableListableBeanFactory.registerCustomEditor(User.class, UserPropertyEditor.class) ;
    }

    //  非 web环境下使用PropertyEditor
    //    // 获取BeanFactory对象
    //    ConfigurableListableBeanFactory beanFactory ;
    //
    //    Dto target = new Dto() ;
    //    DataBinder binder = new DataBinder(target) ;
    //    beanFactory.copyRegisteredEditorsTo(binder) ;
    //    MutablePropertyValues pvs = new MutablePropertyValues() ;
    //    pvs.add("user", "22,张三") ;
    //    binder.bind(pvs) ;
}
