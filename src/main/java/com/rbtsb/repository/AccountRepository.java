package com.rbtsb.repository;

import com.rbtsb.entities.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Account JPA Repository
 *
 * @author Kent
 */
public interface AccountRepository extends MasterEntityRepository<Account> {

    /**
     * Find account by username
     *
     * @param username the username of account
     * @return the Account entity
     */
    @Query("select a from Account a where a.deleted = false and a.username = ?1")
    Account findByUsername(String username);

    @Query("select a from Account a where a.deleted = false and a.id = ?1 and a.resetToken = ?2")
    Account findByIdAndToken(String id, String resetToken);

    @Query("select a from Account a where a.deleted = false and a.email = ?1")
    Account findByEmail(String email);

    @Query("select a from Account a where a.deleted = false and a.role.id = ?1")
    List<Account> findFilteredAccount(String roleId);

    /**
     * Find account by username
     *
     * @param username the username of account
     * @return the Account entity
     */
    @Query("select a from Account a where a.deleted = false and a.username = ?1")
    Optional<Account> findByUsernameReturnOptional(String username);


}
