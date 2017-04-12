package org.ksyzz.service;

import org.ksyzz.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fengqian on 2017/4/11.
 */
@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
}
