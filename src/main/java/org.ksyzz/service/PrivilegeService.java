package org.ksyzz.service;

import org.ksyzz.entity.Account;
import org.ksyzz.enums.AccountType;
import org.ksyzz.exception.PrivilegeException;
import org.springframework.stereotype.Service;

import static org.ksyzz.util.ErrorMessage.noPrivilege;

/**
 * Created by fengqian on 2017/4/19.
 */
@Service
public class PrivilegeService {
    public boolean assertPrivilege(Account account){
        if (account.getAccountType() == AccountType.TEACHER){
            return true;
        }else {
            throw new PrivilegeException(noPrivilege);
        }
    }
}
