package thread;

/**
 * @Description: 多线程手撕：循环顺序执行三个线程的题，输出ABCABCABCABC…我们创建三个线程，每个线程负责打印一个字符（A、B、C）。
 * 通过使用一个共享的对象作为锁，来控制每个线程的执行顺序。当一个线程打印完它的字符后，它会通知下一个线程开始执行。
 * @Author: ls
 * @Date: 2024/9/9 19:40
 */
public class ThreadOrder {

    private static final Object lock = new Object();
    private static int current = 1; // 用于控制当前输出的线程
    public static void main(String[] args) throws InterruptedException {
//        Thread threadA = new Thread(new CharPrinter("A", 1));
//        Thread threadB = new Thread(new CharPrinter("B", 2));
//        Thread threadC = new Thread(new CharPrinter("C", 3));
//        threadA.start();
//        threadB.start();
//        threadC.start();
        ThreadOrder threadOrder = new ThreadOrder();
        threadOrder.threadStopDanger();
    }

    public void threadStopDanger() throws InterruptedException {
        Object o1=new Object();
        Object o2=new Object();
        Thread t1=new Thread(()->{
            synchronized (o1)
            {
                synchronized (o2)
                {
                    try {
                        System.out.println("t1获取到锁");
                        Thread.sleep(5000);
                        System.out.println("t1结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        Thread.sleep(1000);
        Thread t2=new Thread(()->{
            synchronized (o1)
            {
                synchronized (o2)
                {
                    try {
                        System.out.println("t2获取到锁");
                        Thread.sleep(5000);
                        System.out.println("t2结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t2.start();
        t1.stop();
    }
    static class CharPrinter implements Runnable {
        private final String charToPrint;
        private final int order;
        public CharPrinter(String charToPrint, int order) {
            this.charToPrint = charToPrint;
            this.order = order;
        }
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) { // 输出10次
                synchronized (lock) {
                    while (current != order) {
                        try {
                            lock.wait(); // 等待通知
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.print(charToPrint); // 打印字符
                    current++; // 更新当前执行顺序
                    if (current > 3) {
                        current = 1; // 重置顺序
                    }
                    lock.notifyAll(); // 通知下一个线程
                }
            }
        }
    }
}
