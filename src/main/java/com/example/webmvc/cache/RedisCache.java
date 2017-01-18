package com.example.webmvc.cache;

import com.example.webmvc.entity.Role;
import com.example.webmvc.tool.ProtoStuffSerializerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class RedisCache {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public String retrieve(@PathVariable String key) throws ClassNotFoundException {
        byte[] value = redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] value = connection.get(key.getBytes());
                return value;
            }
        });
        if (value == null) {
            return null;
        }
        return new String(value);
    }

    public boolean putCache(String key, Role role) {
        final byte[] bkey = key.getBytes();
        final byte[] bvalue = ProtoStuffSerializerUtil.serialize(role);
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.setNX(bkey,bvalue);
                return true;
            }
        });
        return result;
    }

    public <T> Object getCache(String key,Class<T> targetClass){
        return redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] value= connection.get(key.getBytes());
                return ProtoStuffSerializerUtil.deserialize(value,targetClass);
            }
        });

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
