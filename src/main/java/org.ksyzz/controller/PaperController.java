package org.ksyzz.controller;

import org.ksyzz.entity.Account;
import org.ksyzz.entity.Paper;
import org.ksyzz.info.PaperInfo;
import org.ksyzz.service.AccountTokenService;
import org.ksyzz.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fengqian on 2017/5/2.
 */
@Controller
@RequestMapping("/paper")
public class PaperController {
    @Autowired
    PaperService paperService;
    @Autowired
    AccountTokenService accountTokenService;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public PaperInfo addPaper(
            @RequestHeader("token") String token,
            @RequestBody PaperInfo paperInfo
    ){
        Account account = accountTokenService.getAccountByToken(token);
        Paper paper = paperService.addPaper(account, paperInfo);
        return new PaperInfo(paper);
    }
}
