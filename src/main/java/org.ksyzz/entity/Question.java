package org.ksyzz.entity;

import org.ksyzz.enums.QuestionType;
import org.ksyzz.info.QuestionInfo;

import javax.persistence.*;

import java.util.List;


/**
 * 问题
 * Created by fengqian on 2017/4/11.
 */
@Entity
@Table(name = "o_question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //分值
    private int score;
    //问题描述
    private String description;
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;
    @ManyToOne
    private Exam exam;
    //选择题选项
    @OneToMany(fetch = FetchType.LAZY)
    private List<Option> options;
    //选择题的答案

    //答案解析
    private String analysis;
    //问答题答案
    private String essay_solution;

    public Question() {
    }

    public Question(QuestionInfo questionInfo){
        this.description = questionInfo.getDescription();
        this.questionType = questionInfo.getQuestionType();
        this.score = questionInfo.getScore();
        this.analysis = questionInfo.getAnalysis();
        this.essay_solution = questionInfo.getEssay_solution();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
            this.options = options;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getEssay_solution() {
        return essay_solution;
    }

    public void setEssay_solution(String essay_solution) {
        this.essay_solution = essay_solution;
    }
}
