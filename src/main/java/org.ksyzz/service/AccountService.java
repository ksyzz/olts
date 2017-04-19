package org.ksyzz.service;

import org.ksyzz.entity.Account;
import org.ksyzz.enums.AccountType;
import org.ksyzz.exception.NullEntityException;
import org.ksyzz.exception.ConflictException;
import org.ksyzz.repository.AccountRepository;
import org.ksyzz.util.PasswordEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.ksyzz.util.ErrorMessage.*;

/**
 * Created by fengqian on 2017/4/11.
 */
@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    /**
     * 判断帐号密码是否可以登录
     * @param password
     */
    public void assertAccount(Account account, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (account == null){
            throw new NullEntityException(nullAccount);
        }
        if (!PasswordEncrypt.isEqual(password, account.getPassword())){
            throw new NullEntityException(passwordNotCorrect);
        }
    }

    public Account createAccount(String id, String userName, String password, AccountType accountType) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Account account = accountRepository.findOne(id);
        if (account != null){
            throw new ConflictException(accountExist);
        }
        account = new Account();
        account.setAccountType(accountType);
        account.setPassword(PasswordEncrypt.encrypt(password));
        account.setUserName(userName);
        account.setId(id);
        accountRepository.save(account);
        return account;
    }
    public Account findById(String id){
        return accountRepository.findOne(id);
    }

}
