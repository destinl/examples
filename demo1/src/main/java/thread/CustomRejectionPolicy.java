package thread;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: 可以根据具体需求实现自定义的拒绝策略
 * @Author: ls
 * @Date: 2024/8/9 21:23
 */

public class CustomRejectionPolicy implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        // 自定义处理逻辑
        System.out.println("Task " + r.toString() + " rejected");
    }
}
