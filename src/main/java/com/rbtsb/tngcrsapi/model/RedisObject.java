package com.rbtsb.tngcrsapi.model;

public class RedisObject {
    private String id;
    private String name;

    public RedisObject(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RedisObject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
