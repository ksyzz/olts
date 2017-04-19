package org.ksyzz.info;

import org.ksyzz.entity.Account;

/**
 * Created by fengqian on 2017/4/19.
 */
public class AccountInfo {
    private String id;
    private String userName;

    public AccountInfo(Account account) {
        this.id = account.getId();
        this.userName = account.getUserName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
