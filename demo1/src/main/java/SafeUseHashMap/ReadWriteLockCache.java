package SafeUseHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/922:08
 */
//3 读写锁：读读不互斥，读写互斥，写写互斥
public class ReadWriteLockCache {
    // 创建一个 HashMap 来存储缓存的数据
    private Map<String, String> map = new HashMap<>();

    // 创建读写锁对象
    private ReadWriteLock rw = new ReentrantReadWriteLock();

    // 放对象方法：向缓存中添加一个键值对
    public void put(String key, String value) {
        rw.writeLock().lock();
        try{
            map.put(key, value);
        }finally {
            rw.writeLock().unlock();
        }
    }

    // 取对象方法：从缓存中获取一个值
    public String get(String key) {
        // 获取读锁，允许并发读操作
        rw.readLock().lock();
        try {
            // 执行读操作，从 map 中获取值
            return map.get(key);
        } finally {
            // 释放读锁
            rw.readLock().unlock();
        }
    }
}
