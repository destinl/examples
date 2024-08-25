package lock;

/**
 * @Description: Java 简单实现死锁
 * @Author: ls
 * @Date: 2024/8/25 11:27
 */
public class DeadlockExample {
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("Thread 1");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                synchronized (lock1) {
                    System.out.println("Thread 2");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
