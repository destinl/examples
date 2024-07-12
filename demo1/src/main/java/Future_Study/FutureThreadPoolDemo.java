package main.java.Future_Study;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1214:07
 */
public class FutureThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        long startTime = System.currentTimeMillis();
        System.out.println("前面的业务");
        FutureTask futureTask = new FutureTask<>(() -> {
            Thread.sleep(100);
            return "任务1执行完毕";
        });
        threadPool.submit(futureTask);
        FutureTask futureTask2 = new FutureTask<>(() -> {
            Thread.sleep(100);
            return "任务2执行完毕";
        });
        threadPool.submit(futureTask2);
        FutureTask futureTask3 = new FutureTask<>(() -> {
            Thread.sleep(100);
            return "任务3执行完毕";
        });
        threadPool.submit(futureTask3);
        System.out.println("后面的业务");
        System.out.println("获取输出值：");
        System.out.println(futureTask.get());
        System.out.println(futureTask2.get());
        System.out.println(futureTask3.get());
        long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime) + " 毫秒");
        threadPool.shutdown(); // 资源释放
    }
//    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        long startTime = System.currentTimeMillis();
//
//        System.out.println("前面的业务");
////        Thread.sleep(100);
////        Thread.sleep(200);
////        Thread.sleep(300);
//
//        FutureTask futureTask = new FutureTask<>(()->{
//            Thread.sleep(100);
//            return "任务1执行完毕";
//        });
//        new Thread(futureTask).start();
//
//        FutureTask futureTask2 = new FutureTask<>(() -> {
//            Thread.sleep(100);
//            return "任务2执行完毕";
//        });
//        new Thread(futureTask2).start();
//
//        FutureTask futureTask3 = new FutureTask<>(() -> {
//            Thread.sleep(100);
//            return "任务3执行完毕";
//        });
//        new Thread(futureTask3).start();
//
//        System.out.println("后面的业务");
//        System.out.println("获取输出值：");
//        System.out.println(futureTask.get());
//        System.out.println(futureTask2.get());
//        System.out.println(futureTask3.get());
//        long endTime = System.currentTimeMillis();
//        System.out.println("耗时：" + (endTime - startTime) + " 毫秒");
//    }
}
