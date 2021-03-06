package org.ksyzz.info;

import org.ksyzz.entity.Paper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fengqian on 2017/4/19.
 */
public class PaperInfo {
    private int paperId;
    private ExamInfo examInfo;
    private AccountInfo accountInfo;
    private int score;
    private List<AnswerInfo> answerInfos;

    public PaperInfo(Paper paper){
        this.paperId = paper.getId();
        this.accountInfo = new AccountInfo(paper.getAccount());
        this.examInfo = new ExamInfo(paper.getExam());
        this.score = paper.getScore();
        this.answerInfos = paper.getAnswers().stream().map(AnswerInfo :: new).collect(Collectors.toList());
    }

    public ExamInfo getExamInfo() {
        return examInfo;
    }

    public void setExamInfo(ExamInfo examInfo) {
        this.examInfo = examInfo;
    }

    public PaperInfo() {
    }

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<AnswerInfo> getAnswerInfos() {
        return answerInfos;
    }

    public void setAnswerInfos(List<AnswerInfo> answerInfos) {
        this.answerInfos = answerInfos;
    }
}
