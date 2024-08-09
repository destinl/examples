package thread.MixedTasks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/9 21:45
 */
public class MixedTasksExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 提交CPU密集型任务
        executorService.execute(() -> {
            for (int i = 0; i < 1000000; i++) {} // 模拟CPU密集型任务
            System.out.println(Thread.currentThread().getName() + " executed CPU-intensive task");
        });

        // 提交IO密集型任务
        executorService.execute(() -> {
            try {
                Thread.sleep(2000); // 模拟IO密集型任务
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName() + " executed IO-intensive task");
        });

        // 关闭线程池
        executorService.shutdown();
    }
}
