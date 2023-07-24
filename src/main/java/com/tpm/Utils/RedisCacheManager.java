package com.tpm.Utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheManager {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    public void set(String key,Object value,long expire){

        redisTemplate.opsForValue().set(key,value,expire, TimeUnit.SECONDS);
    }

    public Object get(String key){

        return redisTemplate.opsForValue().get(key);
    }


    public Boolean delete(String key){

        return redisTemplate.delete(key);
    }

    public boolean hasKey(String key) {

        return redisTemplate.hasKey(key);
    }
}
