package main.java.SafeUseHashMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/921:54
 */
public class SafeUseHashMap {

//4、Collections.synchronizedMap  : 读写均加锁
//    Collections.synchronizedMap 方法使用了装饰器模式为线程不安全的 HashMap 提供了一个线程安全的装饰器类 SynchronizedMap。
//    通过 SynchronizedMap 来间接的保证对 HashMap 的操作是线程安全，而 SynchronizedMap 底层也是通过 synchronized 关键字来保证操作的线程安全。
//    static Map<Long, User> userMap = Collections.synchronizedMap(new HashMap<Long, User>());

    public static void main(String[] args){
//        1 方法内部：每个线程创建单独的 HashMap（在service层中）
//        Map<String, Integer> parms = new HashMap<>();

    }

//    2 配置数据：初始化单线程写，后续只提供读
    public class SimpleConfig{
        public final Map<String, String> configMap = new HashMap<>();

        public void Configuration(){
            init();
        }

        public void init(){
            configMap.put("key1", "value1");
            configMap.put("key2", "value2");
        }


    public String getConfigMap(String key) {
        return configMap.get(key);
    }
}

}
