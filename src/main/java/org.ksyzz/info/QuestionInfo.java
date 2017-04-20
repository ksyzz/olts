package org.ksyzz.info;

import org.ksyzz.entity.Question;
import org.ksyzz.enums.QuestionType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fengqian on 2017/4/19.
 */
public class QuestionInfo {
    private int id;
    private int score;
    private String description;
    private QuestionType questionType;
    private List<OptionInfo> options;
    private String analysis;
    private String essay_solution;
    
    public QuestionInfo(Question question){
        this.id = question.getId();
        this.score = question.getScore();
        this.description = question.getDescription();
        this.questionType = question.getQuestionType();
        this.analysis = question.getAnalysis();
        this.essay_solution = question.getEssay_solution();
        if (question.getOptions() != null){
            this.options = question.getOptions().stream().map(OptionInfo :: new).collect(Collectors.toList());
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public List<OptionInfo> getOptions() {
        return options;
    }

    public void setOptions(List<OptionInfo> options) {
        this.options = options;
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
