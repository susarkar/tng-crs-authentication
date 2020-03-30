package com.rbtsb.repository.impl;


import com.rbtsb.datatables.*;
import com.rbtsb.entities.MasterEntity;
import com.rbtsb.repository.MasterEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>
 * @param
 * @author kent
 */
public class MasterEntityRepositoryImpl<T extends MasterEntity> extends BaseEntityRepositoryImpl<T>
        implements MasterEntityRepository<T> {

    private EntityManager entityManager;

    public MasterEntityRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#
     * findAll()
     */
    @Override
    public List<T> findAll() {
        return super.findAll((root, query, cb) -> {
            return cb.and(cb.isFalse(root.get("deleted")));
        });
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#
     * findAll(org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<T> findAll(Pageable pageable) {
        return super.findAll((root, query, cb) -> {
            return cb.and(cb.isFalse(root.get("deleted")));
        }, pageable);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#
     * findAll(org.springframework.data.domain.Sort)
     */
    @Override
    public List<T> findAll(Sort sort) {
        return super.findAll((root, query, cb) -> {
            return cb.and(cb.isFalse(root.get("deleted")));
        }, sort);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#
     * findAll(org.springframework.data.jpa.domain.Specification)
     */
    /*@Override
    public List<T> findAll(Specification<T> spec) {
        return super.findAll(Specifications.where(spec).and((root, query, cb) -> {
            return cb.and(cb.isFalse(root.get("deleted")));
        }));
    }*/

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#
     * findAll(org.springframework.data.jpa.domain.Specification,
     * org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return super.findAll(Specification.where(spec).and((root, query, cb) -> {
            return cb.and(cb.isFalse(root.get("deleted")));
        }), pageable);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#
     * findAll(org.springframework.data.jpa.domain.Specification,
     * org.springframework.data.domain.Sort)
     */
    @Override
    public List<T> findAll(Specification<T> spec, Sort sort) {
        return super.findAll(Specification.where(spec).and((root, query, cb) -> {
            return cb.and(cb.isFalse(root.get("deleted")));
        }), sort);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.data.jpa.repository.support.SimpleJpaRepository#count
     * ()
     */
    @Override
    public long count() {
        return count((root, query, cb) -> cb.and(cb.isFalse(root.get("deleted"))));
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.data.jpa.repository.support.SimpleJpaRepository#count
     * (org.springframework.data.jpa.domain.Specification)
     */
    @Override
    public long count(Specification<T> spec) {
        return super.count(Specification.where(spec).and((root, query, cb) -> {
            return cb.and(cb.isFalse(root.get("deleted")));
        }));
    }

    /**
     * @param entity
     * @param <S>
     * @return
     */
    @Transactional
    @Override
    public <S extends T> S save(S entity) {
//        S newEntity = null;
//        AuditRevision auditRevision = new AuditRevision();
//        auditRevision.setModule(entity.getClass().getName());

//        try {
//            if (entity.getId() != null) {
//                auditRevision.setAuditType(AuditType.UPDATE);
//                auditRevision.setModuleId(entity.getId());

//                T old = super.getOne(entity.getId());

//                if (old != null)
//                    auditRevision.setOldValue(old.toString());
//            } else {
//                auditRevision.setAuditType(AuditType.SAVE);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            newEntity = super.save(entity);
//
//            auditRevision.setNewValue(newEntity.toString());
//            entityManager.persist(auditRevision);
//        }

        return super.save(entity);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#
     * delete(java.io.Serializable)
     */
    @Transactional
    @Override
    public void delete(String id) {
        T t = getOne(id);

        if (t.getRestricted()) {
            return;
        }

        t.setDeleted(true);
        saveAndFlush(t);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#
     * delete(java.lang.Object)
     */
    @Transactional
    @Override
    public void delete(T t) {
        if (t.getRestricted()) {
            return;
        }

        t.setDeleted(true);
        saveAndFlush(t);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#
     * delete(java.lang.Iterable)
     */
    @Transactional
    public void delete(Iterable<? extends T> entities) {
        for (T t : entities) {
            if (t.getRestricted()) {
                return;
            }

            t.setDeleted(true);
            saveAndFlush(t);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.rbtsb.repositories.DataTablesRepository#dataTablesFindAll(com.rbtsb.
     * datatables.DataTablesInput)
     */
    @Override
    public DataTablesOutput<T> dataTablesFindAll(DataTablesInput input) {
        return dataTablesFindAll(input, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.rbtsb.repositories.DataTablesRepository#dataTablesFindAll(com.rbtsb.
     * datatables.DataTablesInput,
     * org.springframework.data.jpa.domain.Specification)
     */
    @Override
    public DataTablesOutput<T> dataTablesFindAll(DataTablesInput input, Specification<T> additionalSpecification) {
        DataTablesOutput<T> output = new DataTablesOutput<T>();
        output.setDraw(input.getDraw());

        try {
            output.setRecordsTotal(count(additionalSpecification));

            Page<T> data = findAll(
                    Specification.where(new DataTablesSpecification<T>(input)).and(additionalSpecification),
                    getPageable(input));

            output.setData(data.getContent());
            output.setRecordsFiltered(data.getTotalElements());

        } catch (Exception e) {
            output.setError(e.toString());
            output.setRecordsFiltered(0L);
        }

        return output;
    }

    /**
     * Creates a 'LIMIT .. OFFSET .. ORDER BY ..' clause for the given
     * {@link DataTablesInput}.
     *
     * @param input the {@link DataTablesInput} mapped from the Ajax request
     * @return a {@link Pageable}, must not be {@literal null}.
     */
    private Pageable getPageable(DataTablesInput input) {
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        for (OrderParameter order : input.getOrder()) {
            ColumnParameter column = input.getColumns().get(order.getColumn());
            if (column.getOrderable()) {
                String sortColumn = StringUtils.isEmpty(column.getName()) ? column.getData() : column.getName();
                Sort.Direction sortDirection = Sort.Direction.fromString(order.getDir());
                orders.add(new Sort.Order(sortDirection, sortColumn));
            }
        }
        Sort sort = orders.isEmpty() ? null : Sort.by(orders);

        if (input.getLength() == -1) {
            input.setStart(0);
            input.setLength(Integer.MAX_VALUE);
        }

        return PageRequest.of(input.getStart() / input.getLength(), input.getLength(), sort);

    }

}
