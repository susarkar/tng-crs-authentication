package com.rbtsb.repository;

import com.rbtsb.entities.Action;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Action JPA Repository
 *
 * @author Kent
 */
public interface ActionRepository extends MasterEntityRepository<Action> {

    /**
     * Find Action By Name
     *
     * @param name
     * @return
     */
    Action findByName(String name);

    /**
     * Find Action By Role Id
     *
     * @param roleId
     * @return
     */
    List<Action> findByRoleId(String roleId);

    /**
     * Find Action By Permission Id
     *
     * @param permissionId
     * @return
     */
    @Query(
            "select a from Action a where a.deleted = false and a.permission.id = ?1 order by case a.description when 'Add' then 1 when 'Update' then 2 when 'Delete' then 3 when 'List' then 4 else 5 end")
    List<Action> findByPermissionId(String permissionId);
}
