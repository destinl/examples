package thread.incorrectly_configured;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/9 21:34
 */
public class RejectionPolicyExample {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                1, // 核心线程数
                1, // 最大线程数
                0L, // 空闲线程存活时间
                TimeUnit.MILLISECONDS, // 存活时间单位
                new LinkedBlockingQueue<>(1), // 任务队列
                Executors.defaultThreadFactory(), // 线程工厂
                new ThreadPoolExecutor.AbortPolicy() // 默认拒绝策略
        );

        // 提交三个任务到线程池，第三个任务将被拒绝
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(1000); // 模拟任务执行
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().getName() + " executed task");
            });
        }

        // 关闭线程池
        executorService.shutdown();
    }
}