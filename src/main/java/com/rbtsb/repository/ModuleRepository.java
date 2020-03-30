package com.rbtsb.repository;

import com.rbtsb.entities.Module;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * Module JPA Repository
 *
 * @author Kent
 */
public interface ModuleRepository extends MasterEntityRepository<Module> {

    /**
     * Find Module By Name
     *
     * @param name
     * @return
     */
    Module findByName(String name);

    /**
     * Find Module By Role Id
     *
     * @param roleId
     * @return
     */
    List<Module> findByRoleId(String roleId);

    Set<Module> findByRoleIdOrderByName(String roleId);

	
//	 @Query("select m from Module m where m.deleted = false and m.sector.id = ?1")
//	 List<Module> findBySectorId(String sectorId);
}
