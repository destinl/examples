package thread.DuplicateThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/9 21:41
 */
public class SingletonThreadPoolExample {
    // 单例线程池
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static ExecutorService getExecutorService() {
        return executorService;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            // 使用单例线程池提交任务
            getExecutorService().execute(() -> {
                System.out.println(Thread.currentThread().getName() + " executed task");
            });
        }

        // 关闭线程池
        getExecutorService().shutdown();
    }
}