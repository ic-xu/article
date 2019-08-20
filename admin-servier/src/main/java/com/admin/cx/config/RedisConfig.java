package com.admin.cx.config;

import com.admin.cx.pojo.EmailSendAndRev;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
class RedisConfig {


    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.boot.admin.notify.mail.from}")
    private String sender;

    @Value("${spring.boot.admin.notify.mail.to}")
    private String receiver;


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
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        return new RedisCacheManager(
//                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
//                this.getRedisCacheConfigurationWithTtl(600), // 默认策略，未配置的 key 会使用这个
//                this.getRedisCacheConfigurationMap() // 指定 key 策略
//        );
//    }
//
//    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
//        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
//        redisCacheConfigurationMap.put("1000s", this.getRedisCacheConfigurationWithTtl(1000));
//        redisCacheConfigurationMap.put("3000s", this.getRedisCacheConfigurationWithTtl(3000));
//        redisCacheConfigurationMap.put("5000s", this.getRedisCacheConfigurationWithTtl(5000));
//        redisCacheConfigurationMap.put("8000s", this.getRedisCacheConfigurationWithTtl(8000));
//        redisCacheConfigurationMap.put("10000s", this.getRedisCacheConfigurationWithTtl(10000));
//        redisCacheConfigurationMap.put("13000s", this.getRedisCacheConfigurationWithTtl(13000));
//        redisCacheConfigurationMap.put("15000s", this.getRedisCacheConfigurationWithTtl(15000));
//        redisCacheConfigurationMap.put("18000s", this.getRedisCacheConfigurationWithTtl(18000));
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


    /**
     * retemplate相关配置
     *
     * @param factory
     * @return
     */
    @Bean(name = "redisTemplate")
    public static RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(factory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSeial.setObjectMapper(om);

        // 值采用json序列化
        template.setValueSerializer(jacksonSeial);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());

        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonSeial);
        template.afterPropertiesSet();

        return template;
    }

    /**
     * @apiNote  邮件发送者的配置信息
     */
    @Bean(name = "javaMailSender")
    public JavaMailSenderImpl JavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        return mailSender;
    }

    /**
     * @apiNote  邮件接受者的配置信息
     */
    @Bean(name = "emailSendAndRev")
    public EmailSendAndRev getEmailSendAndRev(){
        EmailSendAndRev emailSendAndRev = new EmailSendAndRev();
        emailSendAndRev.setReceiver(receiver);
        emailSendAndRev.setSender(sender);
        return emailSendAndRev;
    }


}
