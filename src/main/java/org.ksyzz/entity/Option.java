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
    private boolean isSolution = false;
    public Option(){}

    public boolean isSolution() {
        return isSolution;
    }

    public void setSolution(boolean solution) {
        isSolution = solution;
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
