package redis.redisson;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;


import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/25 15:06
 */
@Slf4j
public class RedissonLockUtil {

    private static final RedissonClient REDISSON_CLIENT = null;
    //SpringUtils.getBean(RedissonClient.class);

    private RedissonLockUtil() {
    }

    /**
     * 一致等待直到获取锁并执行
     * @param lockKey
     * @param runnable
     */
    @SneakyThrows
    public static void lock(String lockKey, Runnable runnable) {
        lock(lockKey, Long.MAX_VALUE, runnable);
    }

    /**
     * 如果在指定时间内获取锁失败则什么也不做     *     * @param lockKey     * @param waitTimeSeconds     * @param onAcquire
     */
    @SneakyThrows
    public static void lock(String lockKey, long waitTimeSeconds,
                            Runnable onAcquire) {
        lock(lockKey, onAcquire, null, waitTimeSeconds, TimeUnit.SECONDS);
    }

    /**
     * 如果在指定时间内获取锁失败则执行onAcquireFail     * 如果获取锁成功则执行onAcquire     *     * @param lockKey     * @param waitTimeSeconds     * @param onAcquire     * @param onAcquireFail
     */
    @SneakyThrows
    public static void lock(String lockKey, long waitTimeSeconds,
                            Runnable onAcquire, Runnable onAcquireFail) {
        lock(lockKey, onAcquire, onAcquireFail, waitTimeSeconds,
                TimeUnit.SECONDS);
    }

    @SneakyThrows
    public static void lock(String lockKey, Runnable runnable,
                            Runnable onAcquireFail, long waitTime, TimeUnit timeUnit) {
        RLock lock = REDISSON_CLIENT.getLock(lockKey);

        if (lock.tryLock(waitTime, timeUnit)) {
            log.debug("获取锁成功，key:{}", lockKey);

            try {
                runnable.run();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    log.debug("开始释放锁，key:{}", lockKey);
                    lock.unlock();
                    log.debug("释放锁完成，key:{}", lockKey);
                } else {
                    log.warn("开始强制释放锁，key:{},name:{}", lockKey, lock.getName());
                    lock.forceUnlock();
                    log.warn("强制释放锁完成，key:{}", lockKey);
                }
            }
        } else if (onAcquireFail != null) {
            onAcquireFail.run();
        } else {
            log.warn("获取锁失败，key:{}", lockKey);
        }
    }
}

