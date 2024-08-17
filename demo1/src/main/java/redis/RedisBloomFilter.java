package redis;

import redis.clients.jedis.Jedis;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/17 17:18
 */
public class RedisBloomFilter {

    private static final String BLOOM_FILTER_KEY = "bloom_filter";
    private static final int BITMAP_SIZE = 1000000; // 位图大小
    private static final int[] HASH_SEEDS = {3, 5, 7, 11, 13, 17}; // 多个哈希函数的种子

    private Jedis jedis;
    private List<SimpleHash> hashFunctions;

    public RedisBloomFilter() {
        this.jedis = new Jedis("localhost", 6379);
        this.hashFunctions = new ArrayList<>();
        for (int seed : HASH_SEEDS) {
            hashFunctions.add(new SimpleHash(BITMAP_SIZE, seed));
        }
    }

    // 添加元素到布隆过滤器
    public void add(String value) {
        for (SimpleHash hashFunction : hashFunctions) {
            jedis.setbit(BLOOM_FILTER_KEY, hashFunction.hash(value), true);
        }
    }

    // 检查元素是否可能存在于布隆过滤器中
    public boolean mightContain(String value) {
        for (SimpleHash hashFunction : hashFunctions) {
            if (!jedis.getbit(BLOOM_FILTER_KEY, hashFunction.hash(value))) {
                return false;
            }
        }
        return true;
    }

    // 关闭连接
    public void close() {
        jedis.close();
    }

    // 简单哈希函数
    public static class SimpleHash {
        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(String value) {
            int result = 0;
            byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
            for (byte b : bytes) {
                result = seed * result + b;
            }
            return (cap - 1) & result;
        }
    }

    public static void main(String[] args) {
        RedisBloomFilter bloomFilter = new RedisBloomFilter();

        // 添加元素到布隆过滤器
        bloomFilter.add("user1");
        bloomFilter.add("user2");
        bloomFilter.add("user3");

        // 检查元素是否可能存在
        System.out.println("Does user1 exist? " + bloomFilter.mightContain("user1")); // 输出: true
        System.out.println("Does user4 exist? " + bloomFilter.mightContain("user4")); // 输出: false

        // 关闭连接
        bloomFilter.close();
    }
}