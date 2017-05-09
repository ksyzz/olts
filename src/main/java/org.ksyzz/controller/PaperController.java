package org.ksyzz.controller;

import org.ksyzz.entity.Account;
import org.ksyzz.entity.Exam;
import org.ksyzz.entity.Paper;
import org.ksyzz.info.AccountInfo;
import org.ksyzz.info.PaperInfo;
import org.ksyzz.service.AccountTokenService;
import org.ksyzz.service.ExamService;
import org.ksyzz.service.PaperService;
import org.ksyzz.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    ExamService examService;
    @Autowired
    PrivilegeService privilegeService;
    /**
     * 学生提交答卷
     * @param token
     * @param paperInfo
     * @return
     */
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

    /**
     * 获取个人所有答卷
     */
    @RequestMapping(value = "/get/account/{token}", method = RequestMethod.GET)
    public String getPapersByAccount(
            @PathVariable("token") String token,
            ModelMap modelMap
    ){
        Account account = accountTokenService.getAccountByToken(token);
        List<PaperInfo> papers = paperService.getPaperByAccount(account).stream().map(PaperInfo::new).collect(Collectors.toList());
        modelMap.addAttribute("paperInfos", papers);
        modelMap.addAttribute("account", new AccountInfo(account));
        return "student_papers";

    }

    /**
     * 获取某一考试的所有答卷
     * @param examId
     * @return
     */
    @RequestMapping(value = "/get/exam/{examId}", method = RequestMethod.GET)
    public String getPapersByExam(
            @PathVariable("examId") int examId,
            @RequestParam("token") String token,
            ModelMap modelMap
    ){
        Account account = accountTokenService.getAccountByToken(token);
        privilegeService.assertPrivilege(account);
        Exam exam = examService.getExam(examId);
        List<Paper> papers = paperService.getPaperByExam(exam);
        List<PaperInfo> paperInfos = papers.stream().map(PaperInfo::new).collect(Collectors.toList());
        modelMap.addAttribute("account", account);
        modelMap.addAttribute("paperInfos", paperInfos);
        return "exam_detail";
    }
}
