package main.java.Future_Study;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1214:20
 */

public class FutureDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask<>(() -> {
            Thread.sleep(3000);
            return "执行完毕";
        });
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
        System.out.println(">>>> 继续执行其他业务");
    }
}