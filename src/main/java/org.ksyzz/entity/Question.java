package org.ksyzz.entity;

import org.ksyzz.enums.QuestionType;

import javax.persistence.*;
import javax.security.auth.Subject;

import java.util.Set;

import static javafx.scene.input.KeyCode.J;

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
    //选择题选项
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "o_question_option",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private Set<Option> options;
    //选择题的答案
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "o_question_solution",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private Set<Option> solutions;
    //答案解析
    private String analysis;
    //问答题答案
    private String essay_solution;

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

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        if (this.id != null){
            this.options.clear();
            this.options.addAll(options);
        }else{
            this.options = options;
        }
    }

    public Set<Option> getSolutions() {
        return solutions;
    }

    public void setSolutions(Set<Option> solutions) {
        if (this.id!= null){
            this.solutions.clear();
            this.solutions.addAll(solutions);
        }else {
            this.solutions = solutions;
        }
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
