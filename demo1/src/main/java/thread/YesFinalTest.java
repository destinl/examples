package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class YesFinalTest {
    final int a;
    int b;
    static YesFinalTest testObj;
    static AtomicInteger successCount = new AtomicInteger(0);
    static AtomicInteger failureCount = new AtomicInteger(0);

    public YesFinalTest() {  // 正确的构造方法
        a = 1;
        b = 2;
    }

    public static void newTestObj() {  // 此时线程 A 调用这个方法
        testObj = new YesFinalTest();
    }

    public static void getTestObj() {  // 此时线程 B 执行这个方法
        YesFinalTest object = testObj;
        int a = object.a;
        int b = object.b;
        if (a == 1 && b == 2) {
            successCount.incrementAndGet();
        } else {
            failureCount.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        // 创建线程 A
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                newTestObj();
            }
        });

        // 创建线程 B
        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                getTestObj();
            }
        });

        // 启动线程
        threadA.start();
        threadB.start();

        // 等待线程结束
        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("成功次数: " + successCount.get());
        System.out.println("失败次数: " + failureCount.get());
    }
}