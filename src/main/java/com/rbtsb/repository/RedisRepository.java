package com.rbtsb.repository;

import com.rbtsb.model.RedisObject;

import java.util.Map;

public interface RedisRepository {

    void add(RedisObject redisObject);

    void delete(String id);

    Map<Object, Object> findAllObjects();

    String findById(String id);
}
