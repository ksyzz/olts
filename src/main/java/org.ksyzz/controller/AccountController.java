package org.ksyzz.controller;

import org.ksyzz.entity.Account;
import org.ksyzz.enums.AccountType;
import org.ksyzz.service.AccountService;
import org.ksyzz.service.AccountTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 登录，成功后跳转到个人主页
     * @param id
     * @param password
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(
            @RequestParam("id") String id,
            @RequestParam("password") String password,
            HttpServletRequest request
    ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Account account = accountService.findById(id);
        accountService.assertAccount(account, password);
        accountTokenService.deleToken(account);
        String accountToken = accountTokenService.createToken(account);
        request.getSession().setAttribute("token", accountToken);
        return  account.getAccountType() == TEACHER ? "teacher" : "student";
    }

    /**
     * 注册，成功后跳转到个人主页
     * @param id
     * @param userName
     * @param password
     * @param account_type
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(
            @RequestParam("id") String id,
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            @RequestParam("account_type") String account_type,
            HttpServletRequest request
    ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        AccountType accountType = account_type.equals("student") ? AccountType.STUDENT : TEACHER;
        Account account = accountService.createAccount(id, userName, password, accountType);
        String accountToken = accountTokenService.createToken(account);
        request.getSession().setAttribute("token", accountToken);
        return account_type;
    }

    /**
     * 登出
     * @param request
     */
    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    @ResponseBody
    public void logout(HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("token");
        accountTokenService.deleteToken(token);
        request.getSession().removeAttribute("token");
    }
}
