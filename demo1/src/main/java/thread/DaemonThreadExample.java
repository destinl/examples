package thread;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/7 22:01
 */
public class DaemonThreadExample {

    public static void main(String[] args) {
        // 创建并启动一个守护线程
        Thread daemonThread = new Thread(() -> {
            System.out.println("守护线程启动");
            try {
                while (true) {
                    // 模拟后台任务
                    System.out.println("守护线程运行中");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("守护线程被中断");
            }
        });
        daemonThread.setDaemon(true); // 设置为守护线程
        daemonThread.start();

        // 创建并启动一个用户线程
        Thread userThread = new Thread(() -> {
            System.out.println("用户线程启动");
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println("用户线程运行: " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("用户线程被中断");
            }
            System.out.println("用户线程结束");
        });
        userThread.start();

        // 主线程立即结束
        System.out.println("主线程结束");
    }
}
