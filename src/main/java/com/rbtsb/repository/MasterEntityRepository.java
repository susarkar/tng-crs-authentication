package com.rbtsb.repository;

import com.rbtsb.datatables.DataTablesInput;
import com.rbtsb.datatables.DataTablesOutput;
import com.rbtsb.entities.MasterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Override existing JPA Spring Data methods
 *
 * @param <T>
 * @author kent
 */
@NoRepositoryBean
public interface MasterEntityRepository<T extends MasterEntity> extends org.springframework.data.jpa.repository.JpaRepository<T, String>, org.springframework.data.jpa.repository.JpaSpecificationExecutor<T> {
    /**
     * Returns the filtered list for the given {@link DataTablesInput}.
     *
     * @param input the {@link DataTablesInput} mapped from the Ajax request
     * @return a {@link DataTablesOutput}
     */
    DataTablesOutput<T> dataTablesFindAll(DataTablesInput input);

    /**
     * Returns the filtered list for the given {@link DataTablesInput}.
     *
     * @param input                   the {@link DataTablesInput} mapped from the Ajax request
     * @param additionalSpecification an additional {@link Specification} to apply to the query
     *                                (with an "AND" clause)
     * @return a {@link DataTablesOutput}
     */
    DataTablesOutput<T> dataTablesFindAll(DataTablesInput input,
                                          Specification<T> additionalSpecification);

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
     */
    List<T> findAll();

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.jpa.repository.JpaRepository#findAll(org.
     * springframework.data.domain.Sort)
     */
    List<T> findAll(Sort sort);

    /**
     * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
     *
     * @param pageable
     * @return a page of entities
     */
    Page<T> findAll(Pageable pageable);

    /**
     * Returns all entities matching the given {@link Specification}.
     *
     * @param spec
     * @return
     */
    List<T> findAll(Specification<T> spec);

    /**
     * Returns a {@link Page} of entities matching the given {@link Specification}.
     *
     * @param spec
     * @param pageable
     * @return
     */
    Page<T> findAll(Specification<T> spec, Pageable pageable);

    /**
     * Returns all entities matching the given {@link Specification} and {@link Sort}.
     *
     * @param spec
     * @param sort
     * @return
     */
    List<T> findAll(Specification<T> spec, Sort sort);

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    long count();

    /**
     * Returns the number of instances that the given {@link Specification} will return.
     *
     * @param spec the {@link Specification} to count instances for
     * @return the number of instances
     */
    long count(Specification<T> spec);

    /**
     * Logical deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    void delete(String id);

    /**
     * Logical deletes a given entity.
     *
     * @param entity
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    void delete(T entity);

    /**
     *
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> S save(S entity);

}
