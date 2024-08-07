package thread;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/7 21:52
 */
public class MainThreadEndsExample {
    public static void main(String[] args) {
        Thread userThread = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println("用户线程运行: " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        userThread.start();

        try {
            Thread.sleep(2000); // 让子线程有时间运行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程结束");
    }
}
