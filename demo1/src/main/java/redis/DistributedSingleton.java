package redis;

import redis.clients.jedis.Jedis;

/**
 * @Description: redis实现分布式单例对象
 * @Author: ls
 * @Date: 2024/8/25 14:01
 */

public class DistributedSingleton {
    private static DistributedSingleton instance;

    private DistributedSingleton() {
    }

    public static DistributedSingleton getInstance() {
        if (instance == null) {
            try (Jedis jedis = new Jedis("localhost", 6379)) {
                String lockKey = "singleton_lock";
                String uniqueValue = "locked";
                boolean locked = false;
                try {
                    // 尝试获取分布式锁
                    //locked = jedis.set(lockKey, uniqueValue, "NX", "PX", 10000);
                    locked = Boolean.parseBoolean(jedis.set(lockKey, uniqueValue));
                    if (locked) {
                        // 检查单例对象是否已经存在
                        if (instance == null) {
                            instance = new DistributedSingleton();
                        }
                    } else {
                        // 等待一段时间后再次尝试获取单例对象
                        Thread.sleep(100);
                        return getInstance();
                    }
                } finally {
                    // 释放分布式锁
                    if (locked) {
                        jedis.del(lockKey);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
