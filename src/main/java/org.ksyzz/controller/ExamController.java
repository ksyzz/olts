package org.ksyzz.controller;

import org.ksyzz.entity.Account;
import org.ksyzz.entity.Exam;
import org.ksyzz.exception.ConflictException;
import org.ksyzz.exception.NullEntityException;
import org.ksyzz.info.ExamInfo;
import org.ksyzz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.ksyzz.util.ErrorMessage.nullAccount;
import static org.ksyzz.util.ErrorMessage.repeateExam;

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
    @Autowired
    AccountService accountService;
    @Autowired
    PaperService paperService;
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

    /**
     * 修改试卷
     * @param id
     * @param title
     * @param password
     * @param time_limited
     * @param token
     */
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

    /**
     * 学生参加考试
     * @param examId
     * @param password
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String joinExam(
            @RequestParam("token") String token,
            @RequestParam("examId") int examId,
            @RequestParam("password") String password,
            ModelMap modelMap
    ){
        Account account = accountTokenService.getAccountByToken(token);
        if (account == null){
            throw new NullEntityException(nullAccount);
        }
        Exam exam = examService.joinExam(examId, password);
        if (paperService.isAnswered(account, exam)){
            throw new ConflictException(repeateExam);
        }
        ExamInfo examInfo = new ExamInfo(exam);
        modelMap.addAttribute("examInfo", examInfo);
       return "exam";
    }

    @RequestMapping(value = "/teacher_view", method = RequestMethod.GET)
    public String getExams(
            @RequestParam("token") String token,
            ModelMap modelMap
    ){
        Account account = accountTokenService.getAccountByToken(token);
        privilegeService.assertPrivilege(account);
        modelMap.addAttribute("account", account);
        List<ExamInfo> examInfos = examService.getExamByAccount(account).stream().map(ExamInfo :: new).collect(Collectors.toList());
        modelMap.addAttribute("examInfos", examInfos);
        return "teacher_view";
    }
}
