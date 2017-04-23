package org.ksyzz.info;

import org.ksyzz.entity.Answer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fengqian on 2017/4/19.
 */
public class AnswerInfo {
    private int id;
    private int score;
    private String essay_answer;
    private List<OptionInfo> answers;
    private QuestionInfo questionInfo;

    public AnswerInfo() {
    }

    public AnswerInfo(Answer answer){
        this.id = answer.getId();
        this.score = answer.getScore();
        this.essay_answer = answer.getEssay_answer();
        this.questionInfo = new QuestionInfo(answer.getQuestion());
        if (answer.getAnswers() != null){
            this.answers = answer.getAnswers().stream().map(OptionInfo :: new).collect(Collectors.toList());
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

    public String getEssay_answer() {
        return essay_answer;
    }

    public void setEssay_answer(String essay_answer) {
        this.essay_answer = essay_answer;
    }

    public List<OptionInfo> getAnswers() {
        return answers;
    }

    public void setAnswers(List<OptionInfo> answers) {
        this.answers = answers;
    }

    public QuestionInfo getQuestionInfo() {
        return questionInfo;
    }

    public void setQuestionInfo(QuestionInfo questionInfo) {
        this.questionInfo = questionInfo;
    }
}
