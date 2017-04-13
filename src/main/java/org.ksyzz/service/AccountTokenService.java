package org.ksyzz.service;

import org.ksyzz.entity.Account;
import org.ksyzz.entity.AccountToken;
import org.ksyzz.repository.AccountRepository;
import org.ksyzz.repository.AccountTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by fengqian on 2017/4/13.
 */
@Service
public class AccountTokenService {
    @Autowired
    AccountTokenRepository accountTokenRepository;
    @Autowired
    AccountRepository accountRepository;

    /**
     * 删除和account关联的token
     */
    public void deleToken(Account account){
        AccountToken accountToken = accountTokenRepository.findByAccount(account);
        if (accountToken != null) {
            accountTokenRepository.delete(accountToken);
        }
    }

    /**
     * 生成令牌
     * @return
     */
    public AccountToken createToken(Account account){
        AccountToken accountToken = new AccountToken();
        accountToken.setToken(generateToken());
        accountToken.setAccount(account);
        accountTokenRepository.save(accountToken);
        return accountToken;
    }

    public String generateToken(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
