package com.rbtsb.repository.impl;


import com.rbtsb.entities.MasterEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

/**
 * @param <T>
 * @param
 * @author kent
 */
public class BaseEntityRepositoryImpl<T extends MasterEntity> extends SimpleJpaRepository<T, String>
        implements org.springframework.data.jpa.repository.JpaRepository<T, String>, org.springframework.data.jpa.repository.JpaSpecificationExecutor<T> {

    public BaseEntityRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }
}
