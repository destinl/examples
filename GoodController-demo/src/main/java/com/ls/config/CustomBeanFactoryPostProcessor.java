package com.ls.config;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/10 18:06
 */
public class CustomBeanFactoryPostProcessor {

    @Bean
    public ConversionService conversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        conversionService.addConverter((org.springframework.core.convert.converter.Converter<?, ?>) new StringToDateConverter()) ;
        return conversionService;
    }
    public class StringToDateConverter implements Converter<String, Date> {
        public Date convert(String source) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse(source) ;
            } catch (ParseException e) {
                throw new RuntimeException(e) ;
            }
        }

        @Override
        public JavaType getInputType(TypeFactory typeFactory) {
            return null;
        }

        @Override
        public JavaType getOutputType(TypeFactory typeFactory) {
            return null;
        }
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer() ;
        // 是否忽略不可解析的占位符
        pspc.setIgnoreUnresolvablePlaceholders(true) ;
        return pspc ;
    }
}
