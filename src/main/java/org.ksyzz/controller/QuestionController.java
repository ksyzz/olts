package org.ksyzz.controller;

import org.ksyzz.entity.Account;
import org.ksyzz.entity.Question;
import org.ksyzz.info.QuestionInfo;
import org.ksyzz.service.AccountTokenService;
import org.ksyzz.service.PrivilegeService;
import org.ksyzz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fengqian on 2017/4/20.
 */
@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    AccountTokenService accountTokenService;
    @Autowired
    PrivilegeService privilegeService;

    /**
     * 添加一个问题
     * @param token
     * @param questionInfo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public QuestionInfo addQuestion(
            @RequestHeader("token") String token,
            @RequestBody QuestionInfo questionInfo
    ){
        Account account = accountTokenService.getAccountByToken(token);
        privilegeService.assertPrivilege(account);
        Question question = questionService.createQuestion(questionInfo);
        return new QuestionInfo(question);
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public QuestionInfo modifyQuestion(
            @PathVariable("id") int id,
            @RequestHeader("token") String token,
            @RequestBody QuestionInfo questionInfo
    ){
        Account account = accountTokenService.getAccountByToken(token);
        privilegeService.assertPrivilege(account);
        Question question = questionService.modifyQuestion(id, questionInfo);
        return new QuestionInfo(question);
    }
}
