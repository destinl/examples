package thread.MixedTasks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/9 21:45
 */
public class SeparateTasksExample {
    // CPU密集型任务的线程池
    private static final ExecutorService cpuIntensivePool = Executors.newFixedThreadPool(5);
    // IO密集型任务的线程池
    private static final ExecutorService ioIntensivePool = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        // 提交CPU密集型任务
        cpuIntensivePool.execute(() -> {
            for (int i = 0; i < 1000000; i++) {} // 模拟CPU密集型任务
            System.out.println(Thread.currentThread().getName() + " executed CPU-intensive task");
        });

        // 提交IO密集型任务
        ioIntensivePool.execute(() -> {
            try {
                Thread.sleep(2000); // 模拟IO密集型任务
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName() + " executed IO-intensive task");
        });

        // 关闭线程池
        cpuIntensivePool.shutdown();
        ioIntensivePool.shutdown();
    }
}