package thread.HiddenException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/9 21:31
 */
public class CustomThreadFactory implements ThreadFactory {
    private final ThreadFactory defaultFactory = Executors.defaultThreadFactory();

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = defaultFactory.newThread(r);

        // 设置全局异常处理器
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.err.println("Thread " + t.getName() + " threw exception: " + e.getMessage());
        });
        return thread;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2, new CustomThreadFactory());

        // 提交任务到线程池
        executorService.execute(() -> {
            throw new NullPointerException("Test Exception");
        });

        // 关闭线程池
        executorService.shutdown();
    }
}
