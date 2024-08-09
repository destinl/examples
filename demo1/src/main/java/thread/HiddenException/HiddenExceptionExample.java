package thread.HiddenException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/9 21:29
 */
public class HiddenExceptionExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 提交任务到线程池
        executorService.execute(() -> {
//            // 模拟抛出异常
//            throw new NullPointerException("Test Exception");

            try {
                // 模拟抛出异常
                throw new NullPointerException("Test Exception");
            } catch (Exception e) {
                System.err.println("Caught exception: " + e.getMessage());
            }
        });

        // 关闭线程池
        executorService.shutdown();
    }
}