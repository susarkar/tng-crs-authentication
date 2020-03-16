package com.rbtsb.tngcrsapi.repository.impl;

import com.rbtsb.tngcrsapi.model.RedisObject;
import com.rbtsb.tngcrsapi.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class RedisRepositoryImpl implements RedisRepository {
    private static final String KEY = "RedisObject";
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    public void add(final RedisObject redisObject) {
        hashOperations.put(KEY, redisObject.getId(), redisObject.getName());
    }

    public void delete(final String id) {
        hashOperations.delete(KEY, id);
    }

    public String findById(final String id) {
        return (String) hashOperations.get(KEY, id);
    }

    public Map<Object, Object> findAllObjects() {
        return hashOperations.entries(KEY);
    }


}