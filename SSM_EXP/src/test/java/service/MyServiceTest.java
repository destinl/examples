package service;

import com.ls.ssm_exp.service.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class) // 如果您使用 JUnit 4
@SpringBootTest
public class MyServiceTest {

    @Autowired
    private MyService myService; // 假设您的服务类名为 MyService

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void testODtuDevice() {

        // 保存设备信息到数据库
        myService.someTransactionalOperation("Transactional");

        // 这里模拟抛出内存溢出错误
        throw new OutOfMemoryError();
    }
}