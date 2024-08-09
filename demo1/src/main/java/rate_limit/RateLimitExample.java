package rate_limit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/9 22:53
 */
public class RateLimitExample {

    // 创建一个 RateLimiter，设置每秒生成 5 个令牌
    private static final RateLimiter rateLimiter = RateLimiter.create(5.0);

    public static void main(String[] args) {
        // 模拟请求处理
        for (int i = 0; i < 10; i++) {
            // 请求获取令牌
            rateLimiter.acquire();
            // 处理请求
            System.out.println("Request " + i + " processed");
        }
    }
}
