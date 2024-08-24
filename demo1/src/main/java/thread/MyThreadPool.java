package thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 简单的 Java 线程池设计示例
 * @Author: ls
 * @Date: 2024/8/24 13:15
 */
public class MyThreadPool {
    private final int corePoolSize;
    private final int maximumPoolSize;
    private final long keepAliveTime;
    private final BlockingQueue<Runnable> workQueue;
    private final ThreadPoolExecutor threadPool;

    public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.workQueue = workQueue;

        this.threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, workQueue);
    }

    public void submitTask(Runnable task) {
        threadPool.submit(task);
    }

    public void shutdown() {
        threadPool.shutdown();
    }

    public int getActiveCount() {
        return threadPool.getActiveCount();
    }

    // Other methods for monitoring and managing the thread pool
}