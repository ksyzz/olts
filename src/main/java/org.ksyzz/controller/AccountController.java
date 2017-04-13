package org.ksyzz.controller;

import org.ksyzz.entity.Account;
import org.ksyzz.entity.AccountToken;
import org.ksyzz.enums.AccountType;
import org.ksyzz.service.AccountService;
import org.ksyzz.service.AccountTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.ksyzz.enums.AccountType.TEACHER;

/**
 * Created by fengqian on 2017/4/12.
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountTokenService accountTokenService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(
            @RequestParam("id") String id,
            @RequestParam("password") String password,
            ModelMap modelMap
    ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(id);
        Account account = accountService.findById(id);
        accountService.assertAccount(account, password);
        accountTokenService.deleToken(account);
        AccountToken accountToken = accountTokenService.createToken(account);
        modelMap.addAttribute("token", accountToken);
        if (account.getAccountType() == TEACHER){
            return "teacher";
        }else {
            return "student";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(
            @RequestParam("id") String id,
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            @RequestParam("account_type") String account_type
    ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        AccountType accountType = account_type.equals("student") ? AccountType.STUDENT : TEACHER;
        Account account = accountService.createAccount(id, userName, password, accountType);
        AccountToken accountToken = accountTokenService.createToken(account);
        if (accountType == TEACHER)
            return "teacher";
        else
            return "student";
    }
}
