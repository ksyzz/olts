package org.ksyzz.repository;

import org.ksyzz.entity.Account;
import org.ksyzz.entity.AccountToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by fengqian on 2017/4/13.
 */
@Repository
public interface AccountTokenRepository extends JpaRepository<AccountToken, String>, JpaSpecificationExecutor<AccountToken> {
    @Query("select t from AccountToken t where t.account = ?1")
    AccountToken findByAccount(Account account);

}
