package com.example.webmvc.cache;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCache {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public final static String CAHCENAME = "cache";// 缓存名
    public final static int CAHCETIME = 60;// 默认缓存时间

    /**
     * 精确删除cahe
     *
     * @param key key
     * @return
     */
    public void deleteCache(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 模糊删除所有key
     *
     * @param pattern
     */
    public void deleteCacheWithPattern(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }

    /**
     * 追加写入列表
     *
     * @param key   列表key
     * @param value 列表内容
     */
    public void list(String key, String value) {
        ListOperations<String, String> list = redisTemplate.opsForList();

        list.rightPush(key, value);
    }

    /**
     * 获取列表
     *
     * @param key   列表key
     * @param start 开始位置
     * @param end   结束位置
     * @return
     */
    public List<String> list(String key, long start, long end) {
        ListOperations<String, String> list = redisTemplate.opsForList();
        return list.range(key, start, end);
    }

    /**
     * 设置一天的缓存
     *
     * @param key
     * @param value
     */
    public void setOneDay(String key, Object value) {
        ValueOperations<String, String> record = redisTemplate.opsForValue();
        record.set(key, JSONObject.toJSONString(value), 24, TimeUnit.HOURS);
    }

    /**
     * 设置缓存
     * @param key
     * @param value
     * @param timeout
     * @param unit
     */
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        ValueOperations<String, String> record = redisTemplate.opsForValue();
        record.set(key, JSONObject.toJSONString(value), timeout, unit);
    }

    public void append(String key, String value) {
        ValueOperations<String, String> record = redisTemplate.opsForValue();
        record.append(key, value);
    }

    public String get(String key) {
        ValueOperations<String, String> record = redisTemplate.opsForValue();
        return record.get(key) == null ? "" : record.get(key);
    }

    /**
     * 清空所有缓存
     */
    public void clearCache() {
        deleteCacheWithPattern(RedisCache.CAHCENAME + "|*");
    }

    public static void main(String[] aa) {
    }

}
