package main.java.AQS;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1123:16
 */
public class BinaryLatch {
    private static final class Sync extends AbstractQueuedSynchronizer {
        // 返回是否处于锁定状态
        protected int tryAcquireShared(int acquires) {
            // 如果闭锁是开的（state == 1），则这个操作成功，否则失败
            return (getState() == 1) ? 1 : -1;
        }

        // 尝试释放共享锁，这将打开闭锁
        protected boolean tryReleaseShared(int releases) {
            // 闭锁打开，state设置为1
            setState(1);
            return true;
        }
    }

    private final Sync sync = new Sync();

    public void release() {
        sync.releaseShared(1);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    public boolean isReleased() {
        return sync.tryAcquireShared(1) >= 0;
    }

    public static void main(String[] args) throws InterruptedException {
        BinaryLatch latch = new BinaryLatch();

        Thread worker = new Thread(() -> {
            System.out.println("Worker is processing...");
            try {
                Thread.sleep(2000); // 模拟工作耗时
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            latch.release();
            System.out.println("Worker has finished processing.");
        });

        Thread waiter = new Thread(() -> {
            System.out.println("Waiter is waiting for the worker to finish...");
            try {
                latch.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Waiter has been notified that worker has finished.");
        });

        worker.start();
        waiter.start();

        worker.join();
        waiter.join();
    }

}
