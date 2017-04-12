package org.ksyzz.entity;

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
    @Column(unique = true)
    private String content;

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
