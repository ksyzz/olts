package org.ksyzz.service;

import org.ksyzz.entity.Option;
import org.ksyzz.entity.Question;
import org.ksyzz.info.QuestionInfo;
import org.ksyzz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengqian on 2017/4/11.
 */
@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    OptionService optionService;
    public Question createQuestion(QuestionInfo questionInfo){
        Question question = new Question(questionInfo);
        if (questionInfo.getOptions() != null){
            List<Option> options = new ArrayList<>();
            List<Option> solutions = new ArrayList<>();
            questionInfo.getOptions().forEach(optionInfo -> {
                options.add(optionService.createOption(optionInfo.getContent()));
            });
            question.setOptions(options);
        }
        questionRepository.save(question);
        return question;
    }
}
