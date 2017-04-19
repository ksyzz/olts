package org.ksyzz.controller;

import org.ksyzz.entity.Account;
import org.ksyzz.service.AccountTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by fengqian on 2017/4/13.
 */
@Controller
public class IndexController {
    @Autowired
    AccountTokenService accountTokenService;

    @RequestMapping("/")
    public String index(){
        return "login";
    }

    @RequestMapping("/teacher")
    public String teacher(
            @RequestParam("token") String token,
            ModelMap modelMap
    ){
        Account account = accountTokenService.getAccountByToken(token);
        modelMap.addAttribute("account", account);
        return "teacher_create";
    }
    @RequestMapping("/student")
    public String student(
            @RequestParam("token") String token,
            ModelMap modelMap
    ){
        Account account = accountTokenService.getAccountByToken(token);
        modelMap.addAttribute("account", account);
        return "student";
    }
}
