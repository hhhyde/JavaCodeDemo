package com.example.webmvc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Scope("prototype")
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @ResponseBody
    @RequestMapping("{key}")
    public String retrieve(@PathVariable String key) {
        String value = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] value = connection.get(key.getBytes());
                return new String(value);
            }
        });
        return value;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "")
    public String redisSet(@RequestParam("key") String key, @RequestParam("value") String value) {
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key.getBytes(), value.getBytes());
                return "200";
            }
        });
    }
}
