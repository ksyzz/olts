package org.ksyzz.entity;

import org.ksyzz.info.OptionInfo;

import javax.persistence.*;

/**
 * Created by fengqian on 2017/4/12.
 */
@Entity
@Table(name = "o_option")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private boolean correct = false;
    public Option(){}

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
