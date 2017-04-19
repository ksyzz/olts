package org.ksyzz.controller;

import org.ksyzz.entity.Account;
import org.ksyzz.entity.Exam;
import org.ksyzz.info.ExamInfo;
import org.ksyzz.service.AccountTokenService;
import org.ksyzz.service.ExamService;
import org.ksyzz.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fengqian on 2017/4/19.
 */
@Controller
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    ExamService examService;
    @Autowired
    AccountTokenService accountTokenService;
    @Autowired
    PrivilegeService privilegeService;

    /**
     * 创建试卷
     * @param title
     * @param password
     * @param time_limited
     * @param token
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ExamInfo createExam(
            @RequestParam("title") String title,
            @RequestParam("password") String password,
            @RequestParam("time_limited") int time_limited,
            @RequestParam("token") String token
    ){
        Account account = accountTokenService.getAccountByToken(token);
        privilegeService.assertPrivilege(account);
        Exam exam = examService.createExam(title, password, time_limited, account);
        return new ExamInfo(exam);
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void modifyExam(
            @PathVariable("id") int id,
            @RequestParam("title") String title,
            @RequestParam("password") String password,
            @RequestParam("time_limited") int time_limited,
            @RequestParam("token") String token
    ){
        Account account = accountTokenService.getAccountByToken(token);
        privilegeService.assertPrivilege(account);
        examService.modifyExam(id, title, password, time_limited);
    }
}
