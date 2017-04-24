package org.ksyzz.service;

import org.ksyzz.entity.Option;
import org.ksyzz.info.OptionInfo;
import org.ksyzz.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengqian on 2017/4/18.
 */
@Service
public class OptionService {
    @Autowired
    OptionRepository optionRepository;

    public Option createOption(String content, boolean correct){
        Option option = new Option();
        option.setContent(content);
        option.setCorrect(correct);
        optionRepository.save(option);

        return option;
    }

    /**
     * 批量创建option
     * @param optionInfos
     * @return
     */
    public List<Option> createOptions(List<OptionInfo> optionInfos){
        List<Option> options = new ArrayList<>();
        optionInfos.forEach(optionInfo -> {
            options.add(createOption(optionInfo.getContent(), optionInfo.isCorrect()));
        });
        return options;
    }
    public void deleteOption(String content){
        Option option = optionRepository.findByContent(content);
        optionRepository.delete(option);
    }
}
