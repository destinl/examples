package Hello;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: ls
 * @Date: 2024/3/3112:47
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private  int capacity;

    public LRUCache(int capacity){
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected  boolean removeEldestEntry(Map.Entry<K, V> eldest){
        return size() > capacity;
    }

    public V get(Object key){
        return super.get(key);
    }

    public V put(K key, V value){
        super.put(key, value);
        return value;
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<Integer, String>(3);
        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");
        System.out.println(cache.get(1));
        cache.put(4, "four");
        System.out.println(cache.get(2));
    }
}
