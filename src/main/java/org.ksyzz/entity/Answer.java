package org.ksyzz.entity;

import javax.persistence.*;

import java.util.List;

import static javafx.scene.input.KeyCode.O;

/**
 * Created by fengqian on 2017/4/11.
 */
@Entity
@Table(name = "o_answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(optional = false)
    private Question question;
    private int score;
    private String essay_answer;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "o_answer_option",
            joinColumns = @JoinColumn(name = "answer_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<Option> answers;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getEssay_answer() {
        return essay_answer;
    }

    public void setEssay_answer(String essay_answer) {
        this.essay_answer = essay_answer;
    }

    public List<Option> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Option> answers) {
        this.answers = answers;
    }
}
