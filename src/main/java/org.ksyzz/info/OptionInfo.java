package org.ksyzz.info;

import org.ksyzz.entity.Option;

/**
 * Created by fengqian on 2017/4/18.
 */
public class OptionInfo {
    private int optionId;
    private String content;
    private boolean correct;
    public OptionInfo(Option option){
        this.optionId = option.getId();
        this.content = option.getContent();
        this.correct = option.isCorrect();
    }
    public OptionInfo(){

    }
    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
