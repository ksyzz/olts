package org.ksyzz.entity;

import javax.persistence.*;

/**
 * 令牌唯一标识account
 * 防止重复登录
 * Created by fengqian on 2017/4/13.
 */
@Entity
@Table(name = "o_accounttoken")
public class AccountToken {
    @Id
    private String token;
    @OneToOne(optional = false)
    private Account account;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
