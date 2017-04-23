package org.ksyzz.service;

import org.ksyzz.entity.Option;
import org.ksyzz.entity.Question;
import org.ksyzz.exception.NullEntityException;
import org.ksyzz.info.QuestionInfo;
import org.ksyzz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.ksyzz.util.ErrorMessage.nullQuestion;

/**
 * Created by fengqian on 2017/4/11.
 */
@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    OptionService optionService;

    /**
     * 创建问题
     * @param questionInfo
     * @return
     */
    public Question createQuestion(QuestionInfo questionInfo){
        Question question = new Question(questionInfo);
        if (questionInfo.getOptions() != null){
            List<Option> options = new ArrayList<>();
            questionInfo.getOptions().forEach(optionInfo -> {
                options.add(optionService.createOption(optionInfo.getContent(), optionInfo.isCorrect()));
            });
            question.setOptions(options);
        }
        questionRepository.save(question);
        return question;
    }

    /**
     * 修改问题
     * @param id
     * @param questionInfo
     * @return
     */
    public Question modifyQuestion(int id, QuestionInfo questionInfo){
        Question question = questionRepository.findOne(id);
        if (question == null){
            throw new NullEntityException(nullQuestion);
        }
        question.setDescription(questionInfo.getDescription());
        question.setAnalysis(question.getAnalysis());
        question.setScore(questionInfo.getScore());
        question.setEssay_solution(questionInfo.getEssay_solution());
        if (questionInfo.getOptions() != null){
            List<Option> options = new ArrayList<>();
            questionInfo.getOptions().forEach(optionInfo -> {
                options.add(optionService.createOption(optionInfo.getContent(), optionInfo.isCorrect()));
            });
            question.setOptions(options);
        }
        questionRepository.save(question);
        return question;
    }
}
