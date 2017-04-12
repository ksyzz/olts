package org.ksyzz.controller;

import org.ksyzz.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fengqian on 2017/4/12.
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            HttpServletRequest request
    ){
        request.getSession().setAttribute("userName", userName);
        return "test";
    }
}
