package com.rbtsb.tngcrsapi.repository;

import com.rbtsb.tngcrsapi.model.RedisObject;

import java.util.Map;

public interface RedisRepository {

    void add(RedisObject redisObject);

    void delete(String id);

    Map<Object, Object> findAllObjects();

    String findById(String id);
}
