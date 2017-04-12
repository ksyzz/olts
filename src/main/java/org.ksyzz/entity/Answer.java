package org.ksyzz.entity;

import javax.persistence.*;

import java.util.Set;

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
    private Paper paper;
    @OneToOne(optional = false)
    private Question question;
    private boolean iscorrect = false;
    private String essay_answer;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "o_answer_option",
            joinColumns = @JoinColumn(name = "answer_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private Set<Option> answers;
    public boolean iscorrect() {
        return iscorrect;
    }

    public void setIscorrect(boolean iscorrect) {
        this.iscorrect = iscorrect;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
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

    public Set<Option> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Option> answers) {
        this.answers = answers;
    }
}
