package com.admin.cx.utils;

import com.admin.cx.SpringBootBeanUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by ubuntu
 * On 19-6-29 上午9:39
 */
@Component
public class RedisUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContextW;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        applicationContextW = applicationContext;
    }

    private RedisTemplate<String, String> redisTemplate;


    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static RedisUtil redisUtil;


    public static RedisUtil getInstance() {

        if (null == redisUtil) {
            synchronized (RedisUtil.class) {
                if (null == redisUtil) {
                    redisUtil = new RedisUtil();

                }
            }
        }
        RedisTemplate<String, String> redisTemplate = (RedisTemplate<String, String>) SpringBootBeanUtil.getBean("redisTemplate");
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }


    /**
     * @param key ，缓存的key值
     * @return time, 失效的时间长度
     * @author chenxu
     * @serialData 19-6-29 - 上午9:41
     * @deprecated expire 指定缓存失效时间
     */
    public Boolean expire(String key, long time) {
        if (time > 0) {
            return redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
        return false;
    }

    /**
     * @param key，需要查寻的key值
     * @return long类型的时间长度，单位秒
     * @author chenxu
     * @serialData 19-6-29 - 上午9:45
     * @deprecated 获取过期时间
     */
    public long getExpire(String key) {
        if (null != key)
            return redisTemplate.getExpire(key);
        return 0;
    }

    /**
     * @Author chenxu
     * @Date 19-6-29 - 上午9:48
     * @Description 判断是否存在key值
     * @parms key
     * @Return boolean
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * @Author chenxu
     * @Date 19-6-29 - 上午9:58
     * @Description 插入一条记录
     * @parms key
     * @Return a
     */
    public void setKey(String key, String value) {
        redisTemplate.opsForValue().set(key, value);

    }

    /**
     * @Author chenxu
     * @Date 19-6-29 - 上午9:51
     * @Description des 删除指定的缓存
     * @parms String 类型的数组，可以是多个key
     * @Return a
     */
    public void deleteKey(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }
    // ================================Map=================================

    /**
     * HashGet
     *
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key, String item) {
        if (item != null)
            return redisTemplate.opsForHash().get(key, item);
        return null;
    }


    /**
     * 获取hashKey对应的所有键值
     *
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }


    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param item 项
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            if (null != value)
                redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 删除hash表中的值
     *
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, String item) {
        redisTemplate.opsForHash().delete(key, item);
    }


    /**
     * 判断hash表中是否有该项的值
     *
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }


    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }


    /**
     * hash递减
     *
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

}
