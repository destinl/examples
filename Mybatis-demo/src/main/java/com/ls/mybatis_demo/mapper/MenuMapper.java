package com.ls.mybatis_demo.mapper;

import org.apache.ibatis.annotations.Param;

import com.ls.mybatis_demo.entity.Menu;

import java.util.List;

public interface MenuMapper {
    List<Menu> findAll();
    Menu findById(@Param("id") Integer id);
    int insert(Menu role);
    int update(Menu role);
    int delete(@Param("id") Integer id);
}

