package com.rbtsb.repository;


import com.rbtsb.entities.MasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Override existing JPA Spring Data methods
 *
 * @param <T>
 * @author kent
 */
@NoRepositoryBean
public interface BaseEntityRepository<T extends MasterEntity> extends JpaRepository<T, String>, JpaSpecificationExecutor<T> {


}
