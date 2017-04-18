package org.ksyzz.info;

import org.ksyzz.entity.Option;

/**
 * Created by fengqian on 2017/4/18.
 */
public class OptionInfo {
    private int id;
    private String content;
    public OptionInfo(Option option){
        this.id = option.getId();
        this.content = option.getContent();
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
