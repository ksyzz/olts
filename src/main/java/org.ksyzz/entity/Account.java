package org.ksyzz.entity;

import org.ksyzz.enums.AccountType;

import javax.persistence.*;

/**
 * Created by fengqian on 2017/4/11.
 */
@Entity
@Table(name = "o_account")
public class Account {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private String userName;
    private String password;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
