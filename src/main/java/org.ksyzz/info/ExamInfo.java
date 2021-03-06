package org.ksyzz.info;

import org.ksyzz.entity.Exam;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fengqian on 2017/4/19.
 */
public class ExamInfo {
    private int examId;
    private String title;
    private String password;
    private int score;
    private int time_limited;
    private List<QuestionInfo> questions;
    //答卷人数
    private int paper_number;
//    private List<PaperInfo> papers;

    public ExamInfo() {
    }

    public ExamInfo(Exam exam){
        this.examId = exam.getId();
        this.title = exam.getTitle();
        this.password = exam.getPassword();
        this.time_limited = exam.getTime_limited();
        this.score = exam.getScore();
        this.paper_number = exam.getPapers() == null ? 0 : exam.getPapers().size();
        if (exam.getQuestions() != null){
            this.questions = exam.getQuestions().stream().map(QuestionInfo :: new).collect(Collectors.toList());
        }
//        if (exam.getPapers() != null){
//            this.papers = exam.getPapers().stream().map(PaperInfo :: new).collect(Collectors.toList());
//        }
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTime_limited() {
        return time_limited;
    }

    public void setTime_limited(int time_limited) {
        this.time_limited = time_limited;
    }

    public List<QuestionInfo> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionInfo> questions) {
        this.questions = questions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPaper_number() {
        return paper_number;
    }

    public void setPaper_number(int paper_number) {
        this.paper_number = paper_number;
    }
}
