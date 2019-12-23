package com.article.service.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.I0Itec.zkclient.ZkClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.TimeUnit;

/**
 * @ Date 19-7-4 - 下午3:44
 */
@Configuration
public class BeanConfig {


    private AppConfiguration appConfiguration;

    @Autowired
    public void setAppConfiguration(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }

    @Bean
    public ZkClient buildZKClient() {
        return new ZkClient(appConfiguration.getZkAddr(), appConfiguration.getZkConnectTimeout());
    }

    @Bean
    public LoadingCache<String, String> buildCache() {
        return CacheBuilder.newBuilder()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) {
                        return null;
                    }
                });
    }


    /**
     * Redis bean
     *
     * @ param factory
     * @ return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


//    @Bean
//    public RouteHandle buildRouteHandle() throws Exception {
//        String routeWay = appConfiguration.getRouteWay();
//        RouteHandle routeHandle = (RouteHandle) Class.forName(routeWay).newInstance();
//        if (routeWay.contains("ConsistentHash")) {
//            //一致性 hash 算法
//            Method method = Class.forName(routeWay).getMethod("setHash", AbstractConsistentHash.class);
//            AbstractConsistentHash consistentHash = (AbstractConsistentHash)
//                    Class.forName(appConfiguration.getConsistentHashWay()).newInstance();
//            method.invoke(routeHandle, consistentHash);
//            return routeHandle;
//        } else {
//
//            return routeHandle;
//
//        }
//
//    }
}
