package thread;

import java.util.concurrent.Semaphore;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/17 19:19
 */
public class AlternatePrinting {

    private Semaphore semaphore1;
    private Semaphore semaphore2;

    public AlternatePrinting() {
        semaphore1 = new Semaphore(1);
        semaphore2 = new Semaphore(0);
    }

    public static void main(String[] args) {
        AlternatePrinting alternatePrinting = new AlternatePrinting();

        new Thread(() -> {
            while (true) {
                alternatePrinting.printThread1();
            }
        }, "Thread 1").start();

        new Thread(() -> {
            while (true) {
                alternatePrinting.printThread2();
            }
        }, "Thread 2").start();
    }

    public void printThread1() {
        try {
            semaphore1.acquire();
            System.out.println("Thread 1 is printing");
            semaphore2.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printThread2() {
        try {
            semaphore2.acquire();
            System.out.println("Thread 2 is printing");
            semaphore1.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
