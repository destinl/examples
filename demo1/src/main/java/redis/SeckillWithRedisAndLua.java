package redis;

import redis.clients.jedis.Jedis;

import java.util.Optional;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/25 11:29
 */

public class SeckillWithRedisAndLua {
    public static void main(String[] args) {
        // 使用 try-with-resources 确保 Jedis 连接正确关闭
        try (Jedis jedis = new Jedis("localhost", 6379)) {
            // Lua 脚本
            String script = "local productKey = KEYS[1]\n" +
                    "local stockKey = ARGV[1]\n" +
                    "local userId = ARGV[2]\n" +
                    "local stock = tonumber(redis.call('hget', productKey, stockKey))\n" +
                    "if stock > 0 then\n" +
                    "   redis.call('hincrby', productKey, stockKey, -1)\n" +
                    "   redis.call('sadd', userId..'_purchased', stockKey)\n" +
                    "   return true\n" +
                    "else\n" +
                    "   return false\n" +
                    "end";

            String productKey = "product:123";
            String stockKey = "quantity";
            int userId = 12345;

            // 初始化库存
            jedis.hset(productKey, stockKey, "110");

            // 执行 Lua 脚本进行秒杀，返回结果包装为 Optional
            Optional<Object> result = Optional.ofNullable(jedis.eval(script, 1, productKey, stockKey,
                    String.valueOf(userId)));

            if (result.isPresent() && result.get().equals(true)) {
                System.out.println("秒杀成功");
            } else {
                System.out.println("秒杀失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 可以添加更详细的错误处理逻辑
        }
    }
}