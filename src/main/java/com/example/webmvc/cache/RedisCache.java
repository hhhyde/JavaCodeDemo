package com.example.webmvc.cache;

import com.example.webmvc.entity.Role;
import com.example.webmvc.tool.ProtoStuffSerializerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisCache {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    /**
     * 放入cache
     * @param key key
     * @param obj value
     * @return
     */
    public <T> boolean putCache(String key, T obj) {
        final byte[] bkey = key.getBytes();
        final byte[] bvalue = ProtoStuffSerializerUtil.serialize(obj);
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.setNX(bkey,bvalue);
                return true;
            }
        });
        return result;
    }

    /**
     * 放入cache
     * @param key key
     * @param obj value
     * @param expireTime n秒后失效
     */
    public <T> void putCache(String key, T obj, final long expireTime) {
        final byte[] bkey = key.getBytes();
        final byte[] bvalue = ProtoStuffSerializerUtil.serialize(obj);
        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.setEx(bkey, expireTime, bvalue);
                return true;
            }
        });
    }

    /**
     * list放入cache
     * @param key key
     * @param objList list
     * @return
     */
    public <T> boolean putCache(String key, List<T> objList) {
        final byte[] bkey = key.getBytes();
        final byte[] bvalue = ProtoStuffSerializerUtil.serializeList(objList);
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.setNX(bkey, bvalue);
            }
        });
        return result;
    }

    /**
     * 获取cache
     * @param key key
     * @param targetClass 返回类型
     * @return
     */
    public <T> Object getCache(String key,Class<T> targetClass){
        return redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] value= connection.get(key.getBytes());
                return ProtoStuffSerializerUtil.deserialize(value,targetClass);
            }
        });
    }

    /**
     * list获取cache
     * @param key key
     * @param targetClass 返回类型
     * @return
     */
    public <T> List<T> getCacheList(final String key, Class<T> targetClass) {
        byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get(key.getBytes());
            }
        });
        if (result == null) {
            return null;
        }
        return ProtoStuffSerializerUtil.deserializeList(result, targetClass);
    }

    public static void main(String[] aa){
        RedisCache redis=new RedisCache();
        Role role=new Role();
        role.setRolename("chris123");
        role.setRoleid(123455L);
        role.setF1("F1");
        role.setLevelId("33");
        redis.putCache("role", role);
    }

}
