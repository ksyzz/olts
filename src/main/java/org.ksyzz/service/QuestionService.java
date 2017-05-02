package org.ksyzz.service;

import org.ksyzz.entity.Exam;
import org.ksyzz.entity.Option;
import org.ksyzz.entity.Question;
import org.ksyzz.exception.NullEntityException;
import org.ksyzz.info.QuestionInfo;
import org.ksyzz.repository.ExamRepository;
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
    @Autowired
    ExamRepository examRepository;
    /**
     * 创建问题
     * @param questionInfo
     * @return
     */
    public Question createQuestion(int examId, QuestionInfo questionInfo){
        Exam exam = examRepository.findOne(examId);
        Question question = new Question(questionInfo);
        question.setExam(exam);
        if (questionInfo.getOptions() != null){
            List<Option> options = optionService.createOptions(questionInfo.getOptions());
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
            List<Option> options = optionService.createOptions(questionInfo.getOptions());
            question.setOptions(options);
        }
        questionRepository.save(question);
        return question;
    }

    public void deleteQuestion(int id){
        questionRepository.delete(id);
    }

    public Question findById(int id){
        return questionRepository.findOne(id);
    }

    /**
     * 获取问题的正确答案
     * @param question
     * @return
     */
    public List<Option> getCorrectOptions(Question question){
        List<Option> correctOption = new ArrayList<>();
        question.getOptions().forEach(option -> {
            if (option.isCorrect())
                correctOption.add(option);
        });
        return correctOption;
    }
}
