package org.ksyzz.controller;

import org.ksyzz.entity.Account;
import org.ksyzz.entity.Exam;
import org.ksyzz.exception.NullEntityException;
import org.ksyzz.info.ExamInfo;
import org.ksyzz.service.AccountService;
import org.ksyzz.service.AccountTokenService;
import org.ksyzz.service.ExamService;
import org.ksyzz.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static org.ksyzz.util.ErrorMessage.nullAccount;

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
    @ResponseBody
    public ExamInfo joinExam(
            @RequestParam("studentId") String studentId,
            @RequestParam("examId") int examId,
            @RequestParam("password") String password,
            HttpServletResponse response
    ){
        Exam exam = examService.joinExam(examId, password);
        if (accountService.findById(studentId) == null){
            throw new NullEntityException(nullAccount);
        }
        // TODO: 2017/4/24 答完试卷后删除cookie
        Cookie cookie = new Cookie("studentId", studentId);
        cookie.setPath("/");
        response.addCookie(cookie);
       return new ExamInfo(exam);
    }
}
