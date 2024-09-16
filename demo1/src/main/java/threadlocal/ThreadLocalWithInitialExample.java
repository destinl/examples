package threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/16 16:16
 */
public class ThreadLocalWithInitialExample {
    public static void main(String[] args) {
        // 使用 ThreadLocal.withInitial 创建一个 ThreadLocal 实例
        ThreadLocal<Integer> threadLocalCounter = ThreadLocal.withInitial(() -> 0);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                int currentValue = threadLocalCounter.get();
                currentValue++;
                threadLocalCounter.set(currentValue);
                System.out.println(Thread.currentThread().getName() + ":值=" + threadLocalCounter.get());
            });
        }
        executorService.shutdown();
    }
}
