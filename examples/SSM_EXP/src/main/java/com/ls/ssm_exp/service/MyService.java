package com.ls.ssm_exp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ls.ssm_exp.repository.MyRepository;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/2911:32
 */
@Service
public class MyService {

    @Autowired
    private MyRepository repository;

    @Transactional(rollbackFor = Exception.class)
    public void someTransactionalOperation(String name) {
        // 调用仓库层的方法
        repository.someDatabaseOperation(name);

        throw new OutOfMemoryError();
        // 业务逻辑...
    }

//    @Test
//    @Transactional(rollbackFor = Exception.class)
//    public void testODtuDevice() {
//
//        // 保存设备信息到数据库
//        someTransactionalOperation("Transactional");
//
//        // 这里模拟抛出内存溢出错误
//        throw new OutOfMemoryError();
//    }

}