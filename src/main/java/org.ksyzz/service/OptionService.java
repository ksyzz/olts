package org.ksyzz.service;

import org.ksyzz.entity.Option;
import org.ksyzz.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fengqian on 2017/4/18.
 */
@Service
public class OptionService {
    @Autowired
    OptionRepository optionRepository;

    public Option createOption(String content){
        Option option = optionRepository.findByContent(content);
        if (option == null){
            option = new Option();
            option.setContent(content);
            optionRepository.save(option);
        }
        return option;
    }

    public void deleteOption(String content){
        Option option = optionRepository.findByContent(content);
        optionRepository.delete(option);
    }
}
