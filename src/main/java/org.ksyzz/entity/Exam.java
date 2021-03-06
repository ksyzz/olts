package org.ksyzz.entity;

import javax.persistence.*;
import java.util.List;

/**
 * 考试
 * Created by fengqian on 2017/4/12.
 */
@Entity
@Table(name = "o_exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private int score=0;
    //凭此密码参加考试
    private String password;
    //考试时长
    private int time_limited;
    @ManyToOne
    private Account account;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "exam")
    private List<Question> questions;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "exam")
    private List<Paper> papers;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Paper> getPapers() {
        return papers;
    }

    public void setPapers(List<Paper> papers) {
        this.papers = papers;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public void addQuestion(Question question){
        this.questions.add(question);
    }

    public void addPaper(Paper paper){
        this.papers.add(paper);
    }
}
