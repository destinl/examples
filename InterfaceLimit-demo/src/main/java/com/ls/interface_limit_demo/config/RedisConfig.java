//package com.ls.interface_limit_demo.config;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisPassword;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.SerializationException;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.io.IOException;
//import java.util.Arrays;
//
///**
// * @Description:
// * @Author: ls
// * @Date: 2024/9/3 22:58
// */
//@Configuration
//public class RedisConfig extends CachingConfigurerSupport {
//    @Value("${spring.data.redis.host}")
//    private String host;
//    @Value("${spring.data.redis.port}")
//    private int port;
//    @Value("${spring.data.redis.password}")
//    private String password;
//    @Value("${spring.data.redis.timeout}")
//    private int timeout;
//    @Value("${spring.redis.jedis.pool.max-idle}")
//    private int maxIdle;
//    @Value("${spring.redis.jedis.pool.max-wait}")
//    private long maxWaitMillis;
//    @Value("${spring.redis.database:0}")
//    private int database;
//    @Bean
//    @ConditionalOnMissingBean(StringRedisTemplate.class)
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        StringRedisTemplate template = new StringRedisTemplate();
//        template.setConnectionFactory(redisConnectionFactory);
//        return template;
//    }
//    @Bean
//    public KeyGenerator wiselyKeyGenerator() {
//        return (target, method, params) -> {
//            StringBuilder sb = new StringBuilder();
//            sb.append(target.getClass().getName());
//            sb.append(method.getName());
//            Arrays.stream(params).map(Object::toString).forEach(sb::append);
//            return sb.toString();
//        };
//    }
//}
//class JacksonRedisSerializer<T> implements RedisSerializer<T> {
//    private Class<T> clazz;
//    private ObjectMapper mapper;
//    JacksonRedisSerializer(Class<T> clazz) {
//        super();
//        this.clazz = clazz;
//        this.mapper = new ObjectMapper();
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//    }
//    @Override
//    public byte[] serialize(T t) throws SerializationException {
//        try {
//            return mapper.writeValueAsBytes(t);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    @Override
//    public T deserialize(byte[] bytes) throws SerializationException {
//        if (bytes.length <= 0) {
//            return null;
//        }
//        try {
//            return mapper.readValue(bytes, clazz);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
