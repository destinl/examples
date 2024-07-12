package main.java.Future_Study;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1213:58
 */
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask<>(new MyThread());
        Thread t1 = new Thread(futureTask);
        t1.start();

        System.out.println(futureTask.get());
    }
}
class MyThread implements Callable {
    @Override
    public String call() throws Exception {
        System.out.println("进入了 call() 方法");
        return "hello world";
    }
}
