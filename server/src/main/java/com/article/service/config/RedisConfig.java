//package com.article.service.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.cache.RedisCacheWriter;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Redis 缓存配置类
// */
//@Configuration
//class RedisConfig {
//
//    /**
//     * redis 中配置缓存的key生成器
//     *
//     * @return
//     */
//    @Bean
//    public KeyGenerator simpleKeyGenerator() {
//        return (o, method, objects) -> {
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append(o.getClass().getSimpleName());
//            stringBuilder.append(".");
//            stringBuilder.append(method.getName());
//            stringBuilder.append("[");
//            for (Object obj : objects) {
//                stringBuilder.append(obj.toString());
//            }
//            stringBuilder.append("]");
//
//            return stringBuilder.toString();
//        };
//    }
//
//    /**
//     * CacheManager
//     *
//     * @param redisTemplate
//     * @return
//     */
//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate) {
//        return new RedisCacheManager(
//                RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory()),
//                this.getRedisCacheConfigurationWithTtl(600), // 默认策略，未配置的 key 会使用这个
//                this.getRedisCacheConfigurationMap() // 指定 key 策略
//
//        );
//    }
//
//    /**
//     * @Author chenxu
//     * @Date 19-6-20 - 上午11:01
//     * @Description 同一封装过期时间，
//     */
//    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
//        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
//        redisCacheConfigurationMap.put("60s", this.getRedisCacheConfigurationWithTtl(60));
//        redisCacheConfigurationMap.put("one_hour", this.getRedisCacheConfigurationWithTtl(60 * 60));
//        redisCacheConfigurationMap.put("one_day", this.getRedisCacheConfigurationWithTtl(60 * 60 * 24));
//        redisCacheConfigurationMap.put("one_week", this.getRedisCacheConfigurationWithTtl(60 * 60 * 24 * 7));
//        redisCacheConfigurationMap.put("Cache-8000s", this.getRedisCacheConfigurationWithTtl(8000));
//        redisCacheConfigurationMap.put("Cache-10000s", this.getRedisCacheConfigurationWithTtl(10000));
//        redisCacheConfigurationMap.put("Cache-13000s", this.getRedisCacheConfigurationWithTtl(13000));
//        redisCacheConfigurationMap.put("Cache-15000s", this.getRedisCacheConfigurationWithTtl(15000));
//        redisCacheConfigurationMap.put("Cache-18000s", this.getRedisCacheConfigurationWithTtl(18000));
//
//
//        return redisCacheConfigurationMap;
//    }
//
//    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
//        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
//                RedisSerializationContext
//                        .SerializationPair
//                        .fromSerializer(jackson2JsonRedisSerializer)
//        ).entryTtl(Duration.ofSeconds(seconds));
//
//        return redisCacheConfiguration;
//    }
//}
