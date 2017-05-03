package org.ksyzz.entity;

import org.hibernate.event.spi.SaveOrUpdateEvent;

import javax.persistence.*;
import javax.security.auth.Subject;
import java.util.List;

/**
 * 试卷
 * Created by fengqian on 2017/4/11.
 */
@Entity
@Table(name = "o_paper")
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(optional = false)
    private Account account;
    @ManyToOne(optional = false)
    private Exam exam;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answer> answers;
    private int score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void calculateScore(int score){
        this.score += score;
    }
}
