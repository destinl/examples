package thread.DuplicateThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/9 21:40
 */
public class DuplicateThreadPoolExample {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            ExecutorService executorService = Executors.newFixedThreadPool(10);

            // 提交任务到线程池
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " executed task");
            });

            // 关闭线程池
            executorService.shutdown();
        }
    }
}
