package com.rbtsb.repository;

import com.rbtsb.entities.Permission;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Permission JPA Repository
 *
 * @author Kent
 */
public interface PermissionRepository extends MasterEntityRepository<Permission> {

    /**
     * Find Permission By Name
     *
     * @param name
     * @return
     */
	@Query("select o from Permission o where o.name = (?1) and o.deleted = false")
    Permission findByName(String name);

    /**
     * Find Permission By Role Id
     *
     * @param roleId
     * @return
     */
    List<Permission> findByRoleId(String roleId);

    /**
     * Find Permission By Module Id
     *
     * @param moduleId
     * @return
     */
    @Query("select p from Permission p where p.deleted = false and p.module.id = ?1")
    List<Permission> findByModuleId(String moduleId);

    /**
     * @param id
     */
	/*
	 * @Modifying
	 * 
	 * @Transactional
	 * 
	 * @Query("delete from Permission p where p.role.id = :id") void
	 * deletByRoleId(@Param("id") String id);
	 */}
