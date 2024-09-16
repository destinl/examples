package threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/16 16:23
 */
public class ThreadLocalMemoryLeakExample {
    static final int THREAD_COUNT = 5;
    public static void main(String[] args) {
        // 创建固定线程数的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    // 创建一个 ThreadLocal 对象
                    ThreadLocal<String> threadLocal = new ThreadLocal<>();
                    threadLocal.set("Some value for this thread");
                    // 这里假设执行一些其他操作后，threadLocal 变量在方法内不再被使用，但由于 Key 是强引用，它可能无法被回收
                    System.out.println(Thread.currentThread().getName() + " set value in ThreadLocal");
                }
            });
        }
        // 关闭线程池
        executorService.shutdown();
    }
}
