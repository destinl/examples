package thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/23 21:17
 */
public class OneChildAferTwoParent {
    private static AtomicInteger parentTask1 = new AtomicInteger(0);
    private static AtomicInteger parentTask2 = new AtomicInteger(0);
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        OneChildAferTwoParent oneChildAferTwoParent = new OneChildAferTwoParent();
//        oneChildAferTwoParent.useAtmoicInterge();
        oneChildAferTwoParent.userCompletableFuture();
    }

    private void useAtmoicInterge(){
        Thread childThread = new Thread(() -> {
            while(true){
                try{
                    synchronized(lock){
                        if(parentTask1.get()== 1 && parentTask2.get() == 1){
                            //do
                            System.out.println("do childThread");
                            break;
                        }
                        //如果条件不满足，就先挂起
                        lock.wait();
                    }
                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
                    e.printStackTrace();
                }
            }
        });
        childThread.start();

        Thread parentThread1 = new Thread(() -> {
            parentTask1.incrementAndGet();
            synchronized(lock){
                //父任务执行完成后唤醒子任务
                lock.notifyAll();
            }
        });
        parentThread1.start();

        Thread parentThread2 = new Thread(() -> {
            parentTask2.incrementAndGet();
            synchronized(lock){
                //父任务执行完成后唤醒子任务
                lock.notifyAll();
            }
        });
        parentThread2.start();
    }

    private void userCompletableFuture(){
        CompletableFuture<Boolean> parentTask1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("doParentTask1");
            return true;
        });

        CompletableFuture<Boolean> parentTask2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("doParentTask2");
            return true;
        });

        CompletableFuture childTask = CompletableFuture.allOf(parentTask1, parentTask2);
        childTask.thenApply(c -> {
            try{
                if(parentTask1.get() && parentTask2.get()){
                    System.out.println("doChildTask");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("child task do error");
            }
            return c;
        });
        childTask.join();
    }
}
