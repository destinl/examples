package thread.ThreadLocalIssue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/9 21:48
 */
public class ThreadLocalSolutionExample {
    private static final ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 提交任务到线程池
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                try {
                    int value = threadLocal.get();
                    System.out.println(Thread.currentThread().getName() + " initial value: " + value);
                    threadLocal.set(value + 1);
                    System.out.println(Thread.currentThread().getName() + " updated value: " + threadLocal.get());
                } finally {
                    // 任务完成后清理ThreadLocal变量
                    threadLocal.remove();
                }
            });
        }

        // 关闭线程池
        executorService.shutdown();
    }
}
