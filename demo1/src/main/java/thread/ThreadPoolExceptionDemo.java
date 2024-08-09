package thread;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/9 20:46
 */

import java.util.concurrent.*;
public class ThreadPoolExceptionDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable task = () -> {
            System.out.println("Executing task in thread: " + Thread.currentThread().getName());
            throw new RuntimeException("Intentional Exception");
        };
        for (int i = 0; i < 5; i++) {
            try {
                executorService.execute(task);
            } catch (Exception e) {
                System.out.println("Caught exception: " + e.getMessage());
            }
        }
        executorService.shutdown();
    }
}
